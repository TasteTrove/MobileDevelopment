package com.example.tastetrove.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.databinding.ItemFavoriteBinding
import com.example.tastetrove.databinding.ItemHistoryBinding

class FavoriteViewAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var listener : FavoriteHolderOnClickListener? = null

    private val diffUtil = object : DiffUtil.ItemCallback<FavoriteFoodModel>() {
        override fun areItemsTheSame(
            oldItem: FavoriteFoodModel,
            newItem: FavoriteFoodModel
        ): Boolean {
            return oldItem.table_id == newItem.table_id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteFoodModel,
            newItem: FavoriteFoodModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setListener(listener: FavoriteHolderOnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun setItems(data: List<FavoriteFoodModel>) {
        asyncListDiffer.submitList(data)
    }

}