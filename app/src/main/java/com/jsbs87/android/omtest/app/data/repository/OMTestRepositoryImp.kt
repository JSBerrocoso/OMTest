package com.jsbs87.android.omtest.app.data.repository

import android.util.Log
import com.jsbs87.android.omtest.app.data.api.OMTestApiService
import com.jsbs87.android.omtest.app.data.entity.MovieEntity
import com.jsbs87.android.omtest.app.data.entity.ResponseEntity
import com.jsbs87.android.omtest.app.data.local.LocalDataSource
import com.jsbs87.android.omtest.app.data.utils.NetworkHandler
import com.jsbs87.android.omtest.app.domain.OMTestRepository
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.functional.map
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.Recommentation
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import kotlin.jvm.Throws

class OMTestRepositoryImp(
    private val apiService: OMTestApiService,
    private val networkHandler: NetworkHandler,
    private val dataSource: LocalDataSource
) : OMTestRepository {

    override suspend fun getMovies(): Either<Failure, List<Movie>> {
        return when (networkHandler.isInternetAvailable()) {
            true ->
                request(
                    apiService.getUnifiedList(),
                    { it.map { filmEntity -> filmEntity.toMovie() } }, emptyList()
                )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun getDetailMovie(externalId: String): Either<Failure, Movie> {
        when (networkHandler.isInternetAvailable()) {
            true -> {
                val result =
                    request(apiService.getVideo(externalId), { it.toMovie() }, MovieEntity.empty())
                return when (result) {
                    is Either.Left -> {
                        Either.Left(result.a)
                    }
                    is Either.Right -> {
                        val movie: Movie = result.b
                        movie.favorite = dataSource.isMovieSaved(movie)
                        Either.Right(movie)
                    }
                }
            }
            false -> return Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun getRecommendations(externalId: String): Either<Failure, List<Recommentation>> {
        return when (networkHandler.isInternetAvailable()) {
            true -> request(
                apiService.getVideoRecommendationList("external_content_id:$externalId"),
                { it.map { recommendationEntity -> recommendationEntity.toRecommendation() } },
                emptyList()
            )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun saveMovieToFavorites(movie: Movie): Either<Failure, Boolean> {
        return dataSource.saveMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie): Either<Failure, Boolean> {
        return try {
            Either.Right(dataSource.removeMovieSaved(movie))
        } catch (exception: Throwable) {
            Log.e(exception.javaClass.simpleName, "Error on deleteFavoriteMovie")
            Either.Left(Failure.DeleteFavoriteMoviesError)
        }
    }

    override suspend fun isMovieFavorite(movie: Movie): Either<Failure, Boolean> {
        return try {
            Either.Right(dataSource.isMovieSaved(movie))
        } catch (exception: Throwable) {
            Log.e(exception.javaClass.simpleName, "Error isMovieFavorite")
            Either.Left(Failure.IsMovieFavoriteError)
        }
    }

    override suspend fun getFavoriteMovies(): Either<Failure, List<Movie>> {
        return try {
            Either.Right(dataSource.getFavoriteMovies())
        } catch (exception: Throwable) {
            Log.e(exception.javaClass.simpleName, "Error on load favorite movies")
            Either.Left(Failure.GetFavoriteMoviesError)
        }
    }

    private fun <T, R> request(
        call: Call<ResponseEntity<T>>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body()?.response ?: default)))
                false -> Either.Left(getFailure(response.errorBody()))
                else -> Either.Left(getFailure(response.errorBody()))
            }
        } catch (exception: Throwable) {
            Log.e(exception.javaClass.simpleName, "Request ${call.request().url}")
            Either.Left(Failure.ServerError)
        }
    }

    private fun getFailure(response: ResponseBody?): Failure {
        return try {
            val stringBody = response?.string()

            val failure =
                Failure.FeatureFailure.getFromCode(getErrorCodeFromBody(stringBody))
                    .apply {
                        message = stringBody
                    }
            failure
        } catch (e: Exception) {
            Failure.ServerError
        }
    }

    @Throws(Exception::class)
    fun getErrorCodeFromBody(stringBody: String?) =
        JSONObject(stringBody).getJSONObject("error").getInt("code")
}