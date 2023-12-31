package com.example.jumpingminds_assignments.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jumpingminds_assignments.models.Article
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.NewsDetailsActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image:ImageView = itemView.findViewById(R.id.news_image)
        val heading:TextView = itemView.findViewById(R.id.headding)
        val author:TextView = itemView.findViewById(R.id.author)
        val date:TextView = itemView.findViewById(R.id.date)
        val card:CardView = itemView.findViewById(R.id.news_card)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_row_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        Glide.with(holder.image.context).load(article.image_url).placeholder(R.drawable.app_logo).into(holder.image)
        holder.author.setText(article.news_site)
        holder.heading.setText(article.title)
        holder.date.setText(format(article.published_at))

        holder.card.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context, NewsDetailsActivity::class.java)
            intent.putExtra("news", article)
            holder.itemView.context.startActivity(intent)
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun format(timestamp:String): String {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM d, HH:mm", Locale.getDefault())

        val date = inputFormat.parse(timestamp)
        val date1 = outputFormat.format(date)
        return date1.toString()
    }
}








