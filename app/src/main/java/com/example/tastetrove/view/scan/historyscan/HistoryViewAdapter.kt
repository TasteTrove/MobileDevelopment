package com.example.tastetrove.view.scan.historyscan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.databinding.ItemHistoryBinding

class HistoryViewAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    private var listener : HistoryHolderOnClickListener? = null

    private val diffUtil = object : DiffUtil.ItemCallback<HistoryModel>() {
        override fun areItemsTheSame(
            oldItem: HistoryModel,
            newItem: HistoryModel
        ): Boolean {
            return oldItem.table_id == newItem.table_id
        }

        override fun areContentsTheSame(
            oldItem: HistoryModel,
            newItem: HistoryModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setListener(listener: HistoryHolderOnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun setItems(data: List<HistoryModel>) {
        asyncListDiffer.submitList(data)
    }

}