package com.example.bankodemia.ui.view.transaction

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.databinding.ItemCardviewContactBinding
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.home.TransactionsAdapter

class UsersAdapter(val users: List<UserDTO>,
                   val isSkeleton: Boolean,
                   val listener: AdapterItemSelected): RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>() {

    inner class UsersHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCardviewContactBinding.bind(view)

        fun render(user: UserDTO) {

        }

        private fun showShimmer() {
            binding.apply {
                userNameTextView.visibility = View.GONE
                accountTextView.visibility = View.GONE
            }
        }

        private fun stopShimmer() {
            binding.apply {
                userNameShimmer.stopShimmer()
                userNameShimmer.visibility = View.GONE
                accountShimmer.stopShimmer()
                accountShimmer.visibility = View.GONE
            }
        }

        private fun setupListeners(user: UserDTO) {
            binding.userCardView.setOnClickListener {
                // TODO - manage user selected
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionsAdapter.TransactionsHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TransactionsAdapter.TransactionsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}