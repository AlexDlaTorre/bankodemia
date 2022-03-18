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
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO

interface AdapterItemSelected {
    fun <T> itemSelected(item: T)
}

class TransactionsAdapter(val transactions: List<TransactionDTO>,
                          val isSkeleton: Boolean,
                          val listener: AdapterItemSelected):
    RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>() {

    inner class TransactionsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCardviewTransactionsBinding.bind(view)

        @RequiresApi(Build.VERSION_CODES.O)
        fun render(transaction: TransactionDTO) {
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

        private fun setupListeners(transaction: TransactionDTO) {
            binding.itemTransactionsCvTransactions.setOnClickListener {
                listener.itemSelected(transaction)
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