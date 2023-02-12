package com.example.flixsterproject3

import android.graphics.drawable.Drawable
import android.support.annotation.Nullable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.flixsterproject3.R.id

class MovieAdapter (
    private val movies: List<BestMovie>,
    private val mListener: ListClickListner?)
    :  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_fragment, parent, false)
        return MovieViewHolder(view,)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView){
        var myMovie : BestMovie? = null
        val myMovieTitle : TextView = mView.findViewById<View>(id.tv_MovieTitle) as TextView
        val myMovieDesc : TextView = mView.findViewById<View>(id.tv_MovieDesc) as TextView
        val myMovieImage : ImageView = mView.findViewById<View>(id.iv_MovieImage) as ImageView
        override fun toString(): String {
            return myMovie.toString() + " '" + myMovieTitle.text + "'"
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.myMovie = movie
        holder.myMovieTitle.text = movie.title
        holder.myMovieDesc.text = movie.overview
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/"+movie.imageURL)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // log exception
                    Log.e("TAG", "Error loading image", e)
                    return false // important to return false so the error placeholder can be placed
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .centerInside()
            .into(holder.myMovieImage)




    }
    override fun getItemCount(): Int {
        return movies.size
    }
}