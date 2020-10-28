package com.jsbs87.android.omtest.app.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.Recommentation
import com.jsbs87.android.omtest.app.presentation.adapter.RecommendationsAdapter
import com.jsbs87.android.omtest.app.presentation.extension.failure
import com.jsbs87.android.omtest.app.presentation.extension.observe
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class DetailMovieFragment : BaseFragment() {

    private val SPAN_COLUMN: Int = 2
    private val viewModel by lifecycleScope.viewModel<DetailMovieViewModel>(this)

    companion object {
        const val EXTRA_EXTERNAL_ID = "extra:externalId"

        fun newInstance(
            entityId: String? = null
        ): DetailMovieFragment {
            return DetailMovieFragment().apply {
                arguments = Bundle().apply { putString(EXTRA_EXTERNAL_ID, entityId) }
            }
        }
    }

    private val movieAdapter =
        RecommendationsAdapter({ recommendation, position ->
            activity?.let {
                DetailMovieActivity.openActivity(
                    it,
                    recommendation.externalContentId + "_PAGE_HD"
                )
            }
        }, {
            if(it.favorite.not()){
                viewModel.saveToFavorite(it)
            }else{
                viewModel.removeFromFavorite(it)
            }
        })

    override fun layoutId(): Int = R.layout.fragment_detail_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.externalId.value = arguments?.getString(EXTRA_EXTERNAL_ID)

        observe(viewModel.movie, ::handleMovie)
        observe(viewModel.recommendations, ::handleRecommendations)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    private fun handleMovie(movie: Movie?) {
        movie?.name?.let {
            if (hasCollapsingToolbarLayout()) {
                setTitleCollapsingToolbarLayout(it)
            } else {
                movie.showTitle = true
            }
        }

        movieAdapter.movie = movie
        movieAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_recommedations.apply {

            layoutManager = GridLayoutManager(context, SPAN_COLUMN).apply {
                spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0) SPAN_COLUMN else 1
                    }
                }
            }
            adapter = movieAdapter
        }
        viewModel.loadDetail()
        viewModel.loadRecommendations()
    }

    private fun handleRecommendations(movies: List<Recommentation>?) {
        if (movies?.isEmpty()!!) {
            movieAdapter.addAll(emptyList())
        } else {
            movieAdapter.addAll(movies)
        }
    }

}