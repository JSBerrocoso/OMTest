package com.jsbs87.android.omtest.app.data.repository

import android.util.Log
import com.jsbs87.android.omtest.app.data.api.OMTestApiService
import com.jsbs87.android.omtest.app.data.entity.ResponseEntity
import com.jsbs87.android.omtest.app.data.utils.NetworkHandler
import com.jsbs87.android.omtest.app.domain.OMTestRepository
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Film
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import kotlin.jvm.Throws

class OMTestRepositoryImp(
    private val apiService: OMTestApiService,
    private val networkHandler: NetworkHandler
) : OMTestRepository {
    override suspend fun getFilms(): Either<Failure, List<Film>> {
        return when (networkHandler.isInternetAvailable()) {
            true ->
                request(
                    apiService.getFilms(),
                    { it.map { filmEntity -> filmEntity.toFilm() } }, emptyList()
                )
            false -> Either.Left(Failure.NetworkConnection)
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