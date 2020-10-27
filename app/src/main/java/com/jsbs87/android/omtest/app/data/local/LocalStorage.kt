package com.jsbs87.android.omtest.app.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

const val KEY_FAVORITE_MOVIES = "favorites"

class LocalStorage(private val sharedPreferences: SharedPreferences) : LocalDataSource {

    override fun saveMovie(movie: Movie): Either<Failure, Boolean> {
        val moviesSaved = getFavoriteMovies().toMutableList()
        val movieSaved = moviesSaved.find { it.id == movie.id }
        return if (movieSaved != null) {
            Either.Left(Failure.MovieAlreadySaved)
        } else {
            moviesSaved.add(movie)
            saveMovieList(moviesSaved)
            Either.Right(true)
        }
    }

    override fun isMovieSaved(movie: Movie): Boolean {
        return getFavoriteMovies().toMutableList().find { it.id == movie.id } != null
    }

    override fun removeMovieSaved(movie: Movie): Boolean {
        val moviesSaved = getFavoriteMovies().toMutableList()
        val movieSaved = moviesSaved.find { it.id == movie.id }
        return if (movieSaved != null) {
            val resultList = moviesSaved.filter { it.id != movie.id }
            saveMovieList(resultList)
            true
        } else {
            false
        }
    }


    override fun getFavoriteMovies(): List<Movie> {
        val movies: List<Movie>
        val jsonList = sharedPreferences.getString(KEY_FAVORITE_MOVIES, "")
        movies = if (jsonList!!.isEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<Movie>>() {}.type
            Gson().fromJson<MutableList<Movie>>(jsonList, type)
        }
        return movies
    }

    private fun saveMovieList(callLog: List<Movie>) {
        val prefsEditor = sharedPreferences.edit()
        val json = Gson().toJson(callLog)
        prefsEditor.putString(KEY_FAVORITE_MOVIES, json)
        prefsEditor.apply()
    }

}