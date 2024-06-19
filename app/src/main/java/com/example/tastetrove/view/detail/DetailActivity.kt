package com.example.tastetrove.view.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.tastetrove.R
import com.example.tastetrove.data.pref.Food
import com.example.tastetrove.data.response.ListStoryItem
import com.example.tastetrove.databinding.ActivityDetailBinding



class DetailActivity : AppCompatActivity() {
    companion object {
        const val key_food = "key_food"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataFood = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Food>(key_food, Food::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(key_food)
        }

        val tvDetailName: TextView = findViewById(R.id.titleFood)
        val tvDetailDescription: TextView = findViewById(R.id.detailFood)
        val ivDetailPhoto: ImageView = findViewById(R.id.imageFood)

        if (dataFood != null) {
            tvDetailName.text = dataFood.food
            tvDetailDescription.text = dataFood.description
            ivDetailPhoto.setImageResource(dataFood.photo)
        }
    }
}









//@Suppress("DEPRECATION")
//class DetailActivity : AppCompatActivity() {
//
//    private lateinit var bind: ActivityDetailBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        bind = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(bind.root)
//
//        val detail = intent.getParcelableExtra<ListStoryItem>(DETAIL_STORY) as ListStoryItem
//        setupAction(detail)
//
//        supportActionBar?.show()
//        supportActionBar?.title = "Detail Food"
//    }
//
//    private fun setupAction(detail: ListStoryItem){
//        Glide.with(applicationContext)
//            .load(detail.photoUrl)
//            .into(bind.imgStory)
//        bind.tvName.text = detail.name
//        bind.tvDesc.text = detail.description
//        bind.progressBar.visibility = View.GONE
//    }
//
//    companion object {
//        const val DETAIL_STORY = "detail_story"
//    }
//}