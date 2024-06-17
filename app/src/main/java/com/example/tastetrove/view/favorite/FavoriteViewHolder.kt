package com.example.tastetrove.view.favorite

import androidx.recyclerview.widget.RecyclerView
import com.example.tastetrove.common.ext.setImageExt
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.databinding.ItemFavoriteBinding
import com.example.tastetrove.databinding.ItemHistoryBinding

class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding,
    private val listener: FavoriteHolderOnClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: FavoriteFoodModel) {
        binding.apply {
            tvUsername.text = data.label
            tvLink.text = data.score
            iv.setImageExt(data.image)
            root.setOnClickListener {
                listener?.onClickListener(data)
            }

        }
    }

}