package com.safetrace.safetraceapp.ui.positive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safetrace.safetraceapp.data.model.PositiveContent
import com.safetrace.safetraceapp.databinding.ItemPositiveContentBinding

class PositiveContentAdapter(private val contentList: List<PositiveContent>) :
    RecyclerView.Adapter<PositiveContentAdapter.PositiveContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositiveContentViewHolder {
        val binding = ItemPositiveContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PositiveContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PositiveContentViewHolder, position: Int) {
        holder.bind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    class PositiveContentViewHolder(private val binding: ItemPositiveContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: PositiveContent) {
            binding.tvQuote.text = content.quote
            binding.tvMessage.text = content.message
            // TODO: Ajouter une image motivante ici
        }
    }
}
