package com.example.bankodemia.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.core.five
import com.example.bankodemia.databinding.ItemCardviewContactBinding
import com.example.bankodemia.databinding.ItemCardviewTransactionsBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.home.TransactionsAdapter

interface AdapterItemSelected {
    fun <T>ItemSelected(item: T)
}

class ContactsAdapter(val contactsList: List<ContactDTO>,
                      val isSkeleton: Boolean,
                      val listener: AdapterItemSelected
):
    RecyclerView.Adapter<ContactsAdapter.ContactsHolder>() {

    inner class ContactsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCardviewContactBinding.bind(view)

        @RequiresApi(Build.VERSION_CODES.O)
        fun render(contact: ContactDTO) {
            stopShimmers()
            binding.apply {
                itemContactTvContact.text = contact.shortName
                itemContactTvContactAccount.text = contact.user.id
            }
            setupListeners(contact)
        }

        fun renderSkeleton() {
            showShimmer()
            binding.apply {
                contactShimmer.startShimmer()
                cardShimmer.startShimmer()
            }
        }

        private fun showShimmer() {
            binding.apply {
                itemContactTvContact.visibility = View.GONE
                itemContactTvContactAccount.visibility = View.GONE
            }
        }
        private fun stopShimmers() {
            binding.apply {
                contactShimmer.stopShimmer()
                contactShimmer.visibility = View.GONE
                cardShimmer.stopShimmer()
                cardShimmer.visibility = View.GONE
            }
        }

        private fun setupListeners(contact: ContactDTO) {
            binding.itemContactsCvContacts.setOnClickListener {
                listener.itemSelected(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactsHolder(
            layoutInflater.inflate(
                R.layout.item_cardview_contact,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        if (isSkeleton) {
            holder.renderSkeleton()
        } else {
            holder.render(contactsList[position])
        }
    }

    override fun getItemCount(): Int {
        if (isSkeleton) {
            return Int.five
        } else {
            return contactsList.size
        }
    }
}