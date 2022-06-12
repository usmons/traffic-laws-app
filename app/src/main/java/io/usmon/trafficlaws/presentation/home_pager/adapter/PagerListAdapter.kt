package io.usmon.trafficlaws.presentation.home_pager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.usmon.trafficlaws.databinding.ListItemLawBinding
import io.usmon.trafficlaws.domain.model.Law

// Created by Usmon Abdurakhmanv on 6/11/2022.

class PagerListAdapter(
    private val onEvent: (Event) -> Unit
) : RecyclerView.Adapter<PagerListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemLawBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBindView(law: Law) {
//            onEvent(Event.ItemClick(law))
//            onEvent(Event.DeleteItemClick(law))
//            onEvent(Event.EditItemClick(law))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemLawBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    // Diff Util
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Law>() {
        override fun areItemsTheSame(oldItem: Law, newItem: Law): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Law, newItem: Law): Boolean {
            return oldItem.title == newItem.title && oldItem.isLiked == newItem.isLiked
        }
    })

    fun submitList(list: List<Law>) {
        differ.submitList(list)
    }

    sealed class Event {
        data class ItemClick(val law: Law) : Event()
        data class EditItemClick(val law: Law) : Event()
        data class DeleteItemClick(val law: Law) : Event()
        data class LikeItemClick(val law: Law): Event()
    }
}