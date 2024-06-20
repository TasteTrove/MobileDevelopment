package com.example.tastetrove.view.scan.historyscan

import androidx.recyclerview.widget.RecyclerView
import com.example.tastetrove.common.ext.setImageExt
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.databinding.ItemHistoryBinding

class HistoryViewHolder(
    private val binding: ItemHistoryBinding,
    private val listener: HistoryHolderOnClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: HistoryModel) {
        binding.apply {
            tvUsername.text = data.nama
            tvLink.text = data.lokasi
            iv.setImageExt(data.image)
            root.setOnClickListener {
                listener?.onClickListener(data)
            }

        }
    }

}