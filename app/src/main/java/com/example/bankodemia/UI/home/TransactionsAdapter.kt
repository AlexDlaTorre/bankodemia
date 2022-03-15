package com.example.bankodemia.UI.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.core.five
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileTransactionDTO
import com.facebook.shimmer.ShimmerFrameLayout

class TransactionsAdapter(val transactions: List<UserProfileTransactionDTO>,
                          val isSkeleton: Boolean):
    RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>() {

    class TransactionsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val conceptText: TextView = view.findViewById(R.id.conceptTextView)
        val conceptShimmer: ShimmerFrameLayout = view.findViewById(R.id.concep_shimmer)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val timeShimmer: ShimmerFrameLayout = view.findViewById(R.id.time_shimmer)
        val amountTextView: TextView = view.findViewById(R.id.amountTextView)
        val amountShimmer: ShimmerFrameLayout = view.findViewById(R.id.amount_shimmer)
        val cardView: CardView = view.findViewById(R.id.itemTransactionsCvTransactions)

        @RequiresApi(Build.VERSION_CODES.O)
        fun render(transaction: UserProfileTransactionDTO) {
            stopShimmers()
            conceptText.text = transaction.concept
            timeTextView.text = transaction.formattedTime
            amountTextView.text = transaction.formattedAmount
            setupListeners(transaction)
        }

        fun renderSkeleton() {
            showShimmer()
            conceptShimmer.startShimmer()
            timeShimmer.startShimmer()
            amountShimmer.startShimmer()
        }

        private fun showShimmer() {
            conceptText.visibility = View.GONE
            timeTextView.visibility = View.GONE
            amountTextView.visibility = View.GONE
        }
        private fun stopShimmers() {
            conceptShimmer.stopShimmer()
            conceptShimmer.visibility = View.GONE
            timeShimmer.stopShimmer()
            timeShimmer.visibility = View.GONE
            amountShimmer.stopShimmer()
            amountShimmer.visibility = View.GONE
        }

        private fun setupListeners(transaction: UserProfileTransactionDTO) {
            cardView.setOnClickListener {
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