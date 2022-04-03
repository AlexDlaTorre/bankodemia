package com.example.bankodemia.ui.view.transaction

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.databinding.FragmentSendBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactDeleteDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.ui.SwipeGesture
import com.example.bankodemia.ui.adapters.ContactsAdapter
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.home.HomeFragment
import com.example.bankodemia.ui.view.AddContactFragment
import com.example.bankodemia.ui.view.ProcessingTransactionFragment
import com.example.bankodemia.ui.view.SearchUser.SearchUserFragment
import com.example.bankodemia.ui.view.TransferDetailFragment
import com.example.bankodemia.ui.viewModel.SendViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_send.*

class SendFragment() : Fragment(), AdapterItemSelected {

    private var _binding: FragmentSendBinding? = null
    private val binding get() = _binding!!
    private var contact: ContactDeleteDTO? = null
    private lateinit var sendViewModel: SendViewModel
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sendViewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        _binding = FragmentSendBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator
        setupReclycerViewContacts(mutableListOf(), true)
        sendViewModel.getContactsListData()
        setupObservers()
        setupEvents()
        return binding.root
    }

    fun setupEvents() {
        binding.homeDetailBtnBackToHome.setOnClickListener { view: View ->
            communicator.goTo(HomeFragment())
        }
        binding.sendBtnAdd.setOnClickListener { view: View ->
            communicator.goTo(SearchUserFragment())
        }
        binding.sendIvLogo.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_transferDetailFragment)
        }
    }

    private fun setupObservers() {
        sendViewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is ContactGetDTO) {
                    val contactInfo = uiState.result as ContactGetDTO
                    setupReclycerViewContacts(contactInfo.data.contacts, false)
                }
            }
            is BaseUiState.Error -> {
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
        }
    }

    fun setupReclycerViewContacts(contactsList: List<ContactDTO>, isSkeleton: Boolean) {
        val activity = activity ?: return
//        val contact = contact ?: return
        val adapter = ContactsAdapter(contactsList, isSkeleton, this)

        val swipeGesture = object : SwipeGesture(activity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val contactDto = contactsList[position]
                when(direction){
                    ItemTouchHelper.LEFT ->{

//                        binding.sendIvLogo.setOnClickListener { view: View ->
//                            findNavController().navigate(R.id.action_sendFragment_to_addContactFragment)
//                        }
//                        communicator.goTo(AddContactFragment())

                        communicator.sendData(contactDto,AddContactFragment())

                    }
                    ItemTouchHelper.RIGHT ->{

                        println("POSICION! ${contactDto._id}")
                        sendViewModel.deleteContact(contactDto._id)
                        sendViewModel.getContact(contactsList[position])
                    }
                }

            }


        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(sendRvContacts)

        binding.apply {
            sendRvContacts.layoutManager = LinearLayoutManager(activity)
            sendRvContacts.setHasFixedSize(true)
            sendRvContacts.adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun <T> itemSelected(item: T) {
        communicator.sendData(item, TransferDetailFragment())
        communicator.sendData(item, AddContactFragment())
    }
}