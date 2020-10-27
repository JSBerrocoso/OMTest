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
import com.jsbs87.android.omtest.app.presentation.extension.loadUrl
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(val onClickItem: (Movie, Int) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieVH>() {

    private val items = mutableListOf<Movie>()

    class MovieVH(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<AppCompatImageView>(R.id.image_movie)
        private val title = view.findViewById<AppCompatTextView>(R.id.title_movie)
        private val subtitle = view.findViewById<AppCompatTextView>(R.id.subtitle_movie)


        fun bind(item: Movie) {
            title.text = item.name
            subtitle.text = item.contentProvider
            val photoUrl = item.attachments.find { it.name == "COVER4_1" }?.value
            if (photoUrl?.isNotEmpty()!!) {
                image.loadUrl(photoUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            .let {
                MovieVH(it)
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(items[position])
        holder.itemView.container_item_movie.setOnClickListener {
            onClickItem(items[position], position)
        }
    }

    @UiThread
    fun addAll(list: List<Movie>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}