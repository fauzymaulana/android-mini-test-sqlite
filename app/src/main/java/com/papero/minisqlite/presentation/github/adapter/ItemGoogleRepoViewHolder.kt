package com.papero.minisqlite.presentation.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.papero.minisqlite.databinding.ItemRepoGoogleBinding
import com.papero.minisqlite.domain.entities.GoogleRepoEntity

class ItemGoogleRepoViewHolder(private val binding: ItemRepoGoogleBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(repo: GoogleRepoEntity) {
        binding.apply {
            txtName.text = repo.name
            txtId.text = "ID : " + repo.id.toString()
            txtLinkRepo.text = repo.linkRepo
        }
    }

    companion object {
        fun create(view: ViewGroup): ItemGoogleRepoViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemRepoGoogleBinding.inflate(inflater, view, false)
            return ItemGoogleRepoViewHolder(binding)
        }
    }

}