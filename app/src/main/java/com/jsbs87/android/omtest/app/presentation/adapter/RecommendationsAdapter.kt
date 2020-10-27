package com.jsbs87.android.omtest.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.OMTestMovie
import com.jsbs87.android.omtest.app.domain.model.Recommentation
import com.jsbs87.android.omtest.app.presentation.extension.loadUrl
import kotlinx.android.synthetic.main.item_recommendation.view.*

class RecommendationsAdapter(val onClickItem: (Recommentation, Int) -> Unit) :
    RecyclerView.Adapter<RecommendationsAdapter.DetailViewHolder>() {

    private val items = mutableListOf<Recommentation>()
    var movie: Movie? = null

    class RecommentationVH(view: View) : DetailViewHolder(view) {
        private val image = view.findViewById<AppCompatImageView>(R.id.image_movie)
        private val title = view.findViewById<AppCompatTextView>(R.id.title_movie)
        private val subtitle = view.findViewById<AppCompatTextView>(R.id.subtitle_movie)

        override fun bind(item: OMTestMovie) {
            if (item is Recommentation) {
                title.text = item.name
                val photoUrl = item.images.find { it.name == "COVER4_1" }?.value
                if (photoUrl?.isNotEmpty()!!) {
                    image.loadUrl(photoUrl)
                }
            }
        }
    }

    class HeaderVH(view: View) : DetailViewHolder(view) {
        private val imageHeader = view.findViewById<AppCompatImageView>(R.id.image_movie_detail)
//        private val title = view.findViewById<AppCompatTextView>(R.id.title_movie_header)
        private val subtitle = view.findViewById<AppCompatTextView>(R.id.subtitle_movie_header)
        private val description = view.findViewById<AppCompatTextView>(R.id.description_movie_header)
        private val year = view.findViewById<AppCompatTextView>(R.id.year_movie_header)


        override fun bind(item: OMTestMovie) {
            if (item is Movie) {
//                title.text = item.name
                subtitle.text = this.itemView.context.getString(R.string.keywords,item.keywords)
                description.text = item.description
                year.text = this.itemView.context.getString(R.string.year,item.year.toString())
                val photoUrl =
                    item.attachments.find { it.name == "GENERIC_APP_SLSHOW_3" }?.value ?: ""
                if (photoUrl.isNotEmpty()) {
                    imageHeader.loadUrl(photoUrl)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return when (viewType) {
            0 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_detail_movie, parent, false)
                    .let {
                        HeaderVH(it)
                    }
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recommendation, parent, false)
                    .let {
                        RecommentationVH(it)
                    }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    fun addAll(list: List<Recommentation>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        when (position) {
            0 -> {
                if (holder is HeaderVH) {
                    movie?.let { holder.bind(it) }
                }
            }
            else -> {
                holder.bind(items[position])
                holder.itemView.container_item_movie.setOnClickListener {
                    onClickItem(items[position], position)
                }
            }
        }
    }


    abstract class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: OMTestMovie)
    }

}