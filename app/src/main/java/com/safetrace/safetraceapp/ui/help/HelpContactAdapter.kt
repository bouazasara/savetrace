package com.safetrace.safetraceapp.ui.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safetrace.safetraceapp.data.model.HelpContact
import com.safetrace.safetraceapp.databinding.ItemHelpContactBinding

class HelpContactAdapter(
    private val contacts: List<HelpContact>,
    private val callClickListener: (HelpContact) -> Unit
) : RecyclerView.Adapter<HelpContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemHelpContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position], callClickListener)
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(private val binding: ItemHelpContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: HelpContact, callClickListener: (HelpContact) -> Unit) {
            binding.tvContactName.text = contact.name
            binding.tvContactNumber.text = contact.number
            binding.btnCall.setOnClickListener { callClickListener(contact) }
        }
    }
}
