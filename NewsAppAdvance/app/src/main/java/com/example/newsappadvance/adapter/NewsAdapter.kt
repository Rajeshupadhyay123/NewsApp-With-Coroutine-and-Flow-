package com.example.newsappadvance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsappadvance.R
import com.example.newsappadvance.databinding.ListItemBinding
import com.example.newsappadvance.model.Article
import com.example.newsappadvance.model.News

class NewsAdapter : ListAdapter<Article, NewsAdapter.ViewHolder>(UserDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.author.text = data.author
        holder.content.text = data.content
        holder.title.text = data.title
        val imgUrl = data.urlToImage
        imgUrl.let {
            val imgUrlVar = imgUrl.toUri().buildUpon().scheme("https").build()
            holder.imageUrl.load(imgUrlVar) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val author: TextView = binding.author
        val content: TextView = binding.content
        val title: TextView = binding.title
        val imageUrl: ImageView = binding.newsImage
    }
}

class UserDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.urlToImage == newItem.urlToImage
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}