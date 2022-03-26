package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.R
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.databinding.FragmentCardsBinding
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.ui.viewModel.CardsViewModel
import com.example.bankodemia.ui.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cards.*

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bankList:Array<String>
    private lateinit var viewModel: CardsViewModel
    private lateinit var communicator: FragmentCommunicator


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
        val randomMonth = RandomNumber(1,12).roll().toString()
        val randomDay = RandomNumber(1,30).roll().toString()

        binding.cardsEtCvv.text = randomCvv
        binding.cardsTvValidityDate.text = "$randomMonth/$randomDay"
//        binding.cardsTvCardNumber.text = "543924647664$randomCardNumber"
        viewModel.getUserProfileData()
        setupObservers()



        println(RandomString(bankList).rollBank())
        println(binding.cardsEtCvv.text )

        return binding.root
    }

    private fun setupObservers() {
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is UserProfileDTO) {
                    val userProfileInfo = uiState.result as UserProfileDTO
                    setupCardNumber(userProfileInfo)
                }
            }
            is BaseUiState.Error -> {
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
        }
    }



    fun setupCardNumber(user: UserProfileDTO) {
        binding.apply {
            cardShimmer.stopShimmer()
            cardShimmer.visibility = View.GONE
            cardsTvCardNumber.text = user.data.user.id
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}