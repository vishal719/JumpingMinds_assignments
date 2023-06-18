package com.example.jumpingminds_assignments.UI

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.databinding.ActivityNewsBinding
import com.example.jumpingminds_assignments.databinding.ActivityNewsDetailsBinding
import com.example.jumpingminds_assignments.models.Article
import java.text.SimpleDateFormat
import java.util.Locale

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsDetailsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getSerializableExtra("news") as? Article

        if (article != null) {
            Glide.with(this).load(article.image_url).into(binding.imageNews)
            binding.newsHeading.setText(article.title)
            binding.newsAuthor.setText(article.news_site)
            binding.newsBody.setText(article.summary)
            binding.newsDate.setText(format(article.published_at))
        }
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