package com.example.bankodemia.ui.view.SearchUser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.databinding.ItemCardviewContactBinding
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.ui.home.AdapterItemSelected

class UsersAdapter(val users: List<UserDTO>,
                   val listener: AdapterItemSelected): RecyclerView.Adapter<UsersAdapter.UsersHolder>() {

    inner class UsersHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCardviewContactBinding.bind(view)

        fun render(user: UserDTO) {
            binding.apply {
                contactShimmer.visibility = View.GONE
                cardShimmer.visibility = View.GONE
                itemContactTvContact.text = user.fullName
                itemContactTvContactAccount.text = user.id
            }
            setupListeners(user)
        }

        private fun setupListeners(user: UserDTO) {
            binding.itemContactsCvContacts.setOnClickListener {
                listener.itemSelected(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsersHolder(layoutInflater.inflate(R.layout.item_cardview_contact,
                                                  parent,
                                      false))
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.render(users[position])
    }

    override fun getItemCount(): Int = users.size
}