package com.example.bankodemia.UI.View.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.databinding.FragmentCardsBinding
import com.example.bankodemia.viewModel.home.CardsViewModel


class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cardsViewModel =
            ViewModelProvider(this).get(CardsViewModel::class.java)

        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.cardsTvCardText
        cardsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}