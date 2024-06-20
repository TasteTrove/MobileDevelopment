package com.example.tastetrove.data.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tastetrove.common.ext.setImageExt
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.databinding.ItemFoodBinding
import com.example.tastetrove.view.scan.ResultActivity


//class FoodAdapter(private val listFood: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
//        return ListViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = listFood.size
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (food, description, photo) = listFood[position]
//        holder.imgPhoto.setImageResource(photo)
//        holder.tvText.text = food
//
//        holder.itemView.setOnClickListener {
//            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java).apply {
//                putExtra("key_food", listFood[holder.adapterPosition])
//            }
//            holder.itemView.context.startActivity(intentDetail)
//        }
//    }
//
//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.imgFood)
//        val tvText: TextView = itemView.findViewById(R.id.tvFoodName)
//    }
//}
//







// TODO CLASS FOOD ADAPTER YANG BENER
class FoodAdapter : ListAdapter<FoodsResponseItem, FoodAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val review = getItem(position)
        if (review != null) {
            holder.bind(review)
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


    inner class ItemViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        private var imgStory: ImageView = binding.imgFood
        private var tvName: TextView = binding.tvFoodName

        fun bind(food: FoodsResponseItem) {
            binding.imgFood.setImageExt(food.foodGambars.first().gambar)
            binding.tvFoodName.text = food.nama
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_DETAIL, food.toMLFoodResponse())
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(imgStory, "image"),
                        Pair(tvName, "name"),
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(item: FoodsResponseItem)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodsResponseItem>() {
            override fun areItemsTheSame(oldItem: FoodsResponseItem, newItem: FoodsResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FoodsResponseItem, newItem: FoodsResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}