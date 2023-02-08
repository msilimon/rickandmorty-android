package com.msilimon.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msilimon.rickandmorty.databinding.LoadStateBinding

class CustomLoadStateAdapter : LoadStateAdapter<CustomLoadStateAdapter.LoadStateHolder>() {
    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        return LoadStateHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class LoadStateHolder(binding: LoadStateBinding) :
        RecyclerView.ViewHolder(binding.root)
}