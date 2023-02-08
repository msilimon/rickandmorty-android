package com.msilimon.rickandmorty.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.msilimon.rickandmorty.R
import com.msilimon.rickandmorty.data.CharacterStatus
import com.msilimon.rickandmorty.data.dto.CharacterDto
import com.msilimon.rickandmorty.databinding.ItemCharacterBinding

class CharactersAdapter :
    PagingDataAdapter<CharacterDto, CharactersAdapter.CharacterHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: CharacterDto) {
            Glide.with(itemView.context).load(character.image).into(binding.ivAvatar)
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            binding.tvSpecies.text = character.species
            binding.tvLastLocationValue.text = character.location.name
            when (character.status) {
                CharacterStatus.UNKNOWN.status -> {
                    binding.ivDotStatus.setColorFilter(itemView.context.getColor(R.color.gray))
                }
                CharacterStatus.ALIVE.status -> {
                    binding.ivDotStatus.setColorFilter(itemView.context.getColor(R.color.green))
                }
                CharacterStatus.DEAD.status -> {
                    binding.ivDotStatus.setColorFilter(itemView.context.getColor(R.color.red))
                }
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<CharacterDto>() {
        override fun areItemsTheSame(oldItem: CharacterDto, newItem: CharacterDto): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CharacterDto, newItem: CharacterDto): Boolean {
            return oldItem == newItem
        }
    }
}