package com.example.astonintensiv5.listFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.astonintensiv5.databinding.ItemContactBinding
import com.example.astonintensiv5.model.Contact

class ContactsListAdapter(
    context: Context,
    private val onContactClicked: (position: Int, Contact) -> Unit
) : ListAdapter<Contact, ContactsListAdapter.ContactViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            binding = ItemContactBinding.inflate(layoutInflater, parent, false),
            onContactClicked = onContactClicked
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ContactViewHolder(
        private val binding: ItemContactBinding,
        private val onContactClicked: (position: Int, Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contact) {
            with(binding) {
                contactName.text = item.name
                root.setOnClickListener {
                    onContactClicked(layoutPosition, item)
                }
            }
        }
    }
}
