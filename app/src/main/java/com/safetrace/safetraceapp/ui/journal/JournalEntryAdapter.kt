package com.safetrace.safetraceapp.ui.journal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.safetrace.safetraceapp.data.model.JournalEntry
import com.safetrace.safetraceapp.databinding.ItemJournalEntryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class JournalEntryAdapter : ListAdapter<JournalEntry, JournalEntryAdapter.JournalEntryViewHolder>(JournalEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalEntryViewHolder {
        val binding = ItemJournalEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JournalEntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JournalEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JournalEntryViewHolder(private val binding: ItemJournalEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: JournalEntry) {
            val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
            binding.tvTimestamp.text = sdf.format(Date(entry.timestamp))
            binding.tvEntryText.text = entry.text

            // Afficher les indicateurs de média
            binding.ivPhotoIndicator.visibility = if (entry.photoPath != null) View.VISIBLE else View.GONE
            binding.ivVideoIndicator.visibility = if (entry.videoPath != null) View.VISIBLE else View.GONE
        }
    }
}

class JournalEntryDiffCallback : DiffUtil.ItemCallback<JournalEntry>() {
    override fun areItemsTheSame(oldItem: JournalEntry, newItem: JournalEntry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JournalEntry, newItem: JournalEntry): Boolean {
        return oldItem == newItem
    }
}
