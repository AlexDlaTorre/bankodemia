package com.example.bankodemia.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.core.five
import com.example.bankodemia.databinding.ItemCardviewTransactionsBinding
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileTransactionDTO

class TransactionsAdapter(val transactions: List<UserProfileTransactionDTO>,
                          val isSkeleton: Boolean):
    RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>() {

    class TransactionsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCardviewTransactionsBinding.bind(view)

        @RequiresApi(Build.VERSION_CODES.O)
        fun render(transaction: UserProfileTransactionDTO) {
            stopShimmers()
            binding.apply {
                conceptTextView.text = transaction.concept
                timeTextView.text = transaction.formattedTime
                amountTextView.text = transaction.formattedAmount
            }
            setupListeners(transaction)
        }

        fun renderSkeleton() {
            showShimmer()
            binding.apply {
                concepShimmer.startShimmer()
                timeShimmer.startShimmer()
                amountShimmer.startShimmer()
            }
        }

        private fun showShimmer() {
            binding.apply {
                conceptTextView.visibility = View.GONE
                timeTextView.visibility = View.GONE
                amountTextView.visibility = View.GONE
            }
        }
        private fun stopShimmers() {
            binding.apply {
                concepShimmer.stopShimmer()
                concepShimmer.visibility = View.GONE
                timeShimmer.stopShimmer()
                timeShimmer.visibility = View.GONE
                amountShimmer.stopShimmer()
                amountShimmer.visibility = View.GONE
            }
        }

        private fun setupListeners(transaction: UserProfileTransactionDTO) {
            binding.itemTransactionsCvTransactions.setOnClickListener {
                // TODO - implement event to show transaction detail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TransactionsHolder(layoutInflater.inflate(R.layout.item_cardview_transactions,
            parent,
            false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TransactionsHolder, position: Int) {
        if (isSkeleton) {
            holder.renderSkeleton()
        } else {
            holder.render(transactions[position])
        }
    }

    override fun getItemCount(): Int {
        if (isSkeleton) {
            return Int.five
        } else {
            return transactions.size
        }
    }
}