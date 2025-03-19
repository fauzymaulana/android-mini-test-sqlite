package com.papero.minisqlite.presentation.github.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.papero.minisqlite.core.utilities.OnClickListenerAdapter
import com.papero.minisqlite.domain.entities.GoogleRepoEntity

class GoogleRepoAdapter(private val onClick: OnClickListenerAdapter<GoogleRepoEntity>
): ListAdapter<GoogleRepoEntity, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemGoogleRepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemGoogleRepoViewHolder).bind(item)
        holder.itemView.setOnClickListener {
            onClick.onClick(item)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GoogleRepoEntity>() {
            override fun areItemsTheSame(
                oldItem: GoogleRepoEntity,
                newItem: GoogleRepoEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GoogleRepoEntity,
                newItem: GoogleRepoEntity
            ): Boolean = oldItem == newItem

        }
    }
}