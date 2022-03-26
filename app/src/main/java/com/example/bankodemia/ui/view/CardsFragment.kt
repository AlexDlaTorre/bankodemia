package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.R
import com.example.bankodemia.core.utils.RandomNumber
import com.example.bankodemia.core.utils.RandomString
import com.example.bankodemia.databinding.FragmentCardsBinding
import com.example.bankodemia.ui.viewModel.CardsViewModel
import kotlinx.android.synthetic.main.fragment_cards.*

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bankList:Array<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cardsViewModel =
            ViewModelProvider(this).get(CardsViewModel::class.java)
        bankList = arrayOf("Bankodemia","HSBC","CityBanamex","Bancomer","BBVA")

        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        val randomCvv = RandomNumber(100,199).roll().toString()
        val randomCardNumber = RandomNumber(1000,1999).roll().toString()

        binding.cardsEtCvv.text = randomCvv
        binding.cardsTvCardNumber.text = "543924647664$randomCardNumber"




        println(RandomString(bankList).rollBank())
        println(binding.cardsEtCvv.text )
//        binding.textField123.text = "Hola"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}