package com.jsbs87.android.omtest.app.presentation.util

import com.jsbs87.android.omtest.app.domain.model.Movie

interface TouchableMovieView {
    fun onClickMovie(movie: Movie)
}