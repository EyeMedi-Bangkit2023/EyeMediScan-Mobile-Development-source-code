package com.capstone.mybottomnav.db

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.mybottomnav.databinding.ItemLoadBinding

class LoadingState (private val retry: () -> Unit) : LoadStateAdapter<LoadingState.LoadingStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingStateViewHolder {
        val binding = ItemLoadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding, retry)
    }
    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    class LoadingStateViewHolder(private val binding: ItemLoadBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retry.setOnClickListener { retry.invoke() }
        }
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.error.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retry.isVisible = loadState is LoadState.Error
            binding.error.isVisible = loadState is LoadState.Error
        }
    }
}