package com.example.tastetrove.view.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.databinding.ActivityDetailBinding



//class DetailActivity : AppCompatActivity() {
//    companion object {
//        const val key_food = "key_food"
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//
//        val dataFood = if (Build.VERSION.SDK_INT >= 33){
//            intent.getParcelableExtra<Food>(key_food, Food::class.java)
//        }else{
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<Food>(key_food)
//        }
//
//        val tvDetailName: TextView = findViewById(R.id.titleFood)
//        val tvDetailDescription: TextView = findViewById(R.id.detailFood)
//        val ivDetailPhoto: ImageView = findViewById(R.id.imageFood)
//
//        if (dataFood != null) {
//            tvDetailName.text = dataFood.food
//            tvDetailDescription.text = dataFood.description
//            ivDetailPhoto.setImageResource(dataFood.photo)
//        }
//    }
//}
//








@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var bind: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
//
//        val detail = intent.getParcelableExtra<FoodsResponseItem>(DETAIL_FOOD) as FoodsResponseItem
//        setupAction(detail)

        supportActionBar?.show()
    }

    private fun setupAction(detail: FoodsResponseItem){
        Glide.with(applicationContext)
            .load(detail.foodGambars)
            .into(bind.imageFood)
        bind.titleFood.text = detail.nama
        bind.detailFood.text = detail.deskripsi
        bind.loadingProgressBar.visibility = View.GONE
    }

    companion object {
        const val DETAIL_FOOD = "detail_food"
    }
}