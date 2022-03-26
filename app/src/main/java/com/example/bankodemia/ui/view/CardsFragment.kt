package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.databinding.FragmentCardsBinding
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.ui.viewModel.CardsViewModel
import com.google.android.material.snackbar.Snackbar

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bankList:Array<String>
    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        bankList = arrayOf("Bankodemia","HSBC","CityBanamex","Bancomer","BBVA")

        _binding = FragmentCardsBinding.inflate(inflater, container, false)

        cardsViewModel.getUserProfileData()
        setupObservers()
        setupDateCvv()

        return binding.root
    }



    private fun setupObservers() {
        cardsViewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
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

    fun setupDateCvv() {
        val randomCvv = RandomNumber(100,199).roll().toString()
        val randomMonth = RandomNumber(1,12).roll().toString()
        val randomDay = RandomNumber(1,30).roll().toString()
        binding.apply {
            cardsEtCvv.text = randomCvv
            cardsTvValidityDate.text = "$randomMonth/$randomDay"
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