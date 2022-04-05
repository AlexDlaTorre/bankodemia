package com.example.bankodemia.ui.view.transaction

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.core.eight
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.three
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.core.zero
import com.example.bankodemia.databinding.FragmentSendBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.ui.SwipeGesture
import com.example.bankodemia.ui.adapters.ContactsAdapter
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.home.HomeFragment
import com.example.bankodemia.ui.view.AddContactFragment
import com.example.bankodemia.ui.view.SearchUser.SearchUserFragment
import com.example.bankodemia.ui.view.TransferDetailFragment
import com.example.bankodemia.ui.viewModel.SendViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_send.*
import kotlin.math.abs
import kotlin.math.roundToInt

class SendFragment : Fragment(), AdapterItemSelected {

    private var _binding: FragmentSendBinding? = null
    private val binding get() = _binding!!
    private lateinit var sendViewModel: SendViewModel
    private lateinit var communicator: FragmentCommunicator
    private lateinit var Itemcontact: ContactDTO
    private lateinit var adapter: ContactsAdapter
    private var position: Int = 0

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

    private fun setupEvents() {
        binding.homeDetailBtnBackToHome.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_sendFragment_to_navigation_home)
        }
        binding.sendBtnAdd.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_sendFragment_to_searchUserFragment)
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
        adapter = ContactsAdapter(contactsList, isSkeleton, this)
        val swipeGesture = object : SwipeGesture(activity) {
            val displayMetrics: DisplayMetrics = resources.displayMetrics
            val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp
            val deleteIcon = resources.getDrawable(R.drawable.ic_delete, null)
            val editIcon = resources.getDrawable(R.drawable.ic_edit, null)
            val deleteColor = resources.getColor(android.R.color.holo_red_light)
            val editColor = resources.getColor(android.R.color.holo_green_light)
            val sendFragmentLayout = binding.sendFragmentLayout

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                position = viewHolder.adapterPosition
                Itemcontact = contactsList[position]
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        communicator.sendData(Itemcontact, AddContactFragment())
                    }
                    ItemTouchHelper.RIGHT -> {
                        showAlert()
                    }
                }
                Snackbar.make(
                    sendFragmentLayout,
                    if (direction == ItemTouchHelper.RIGHT) R.string.delete else R.string.edit,
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                when {
                    abs(dX) < width / Int.three -> canvas.drawColor(Color.GRAY)
                    dX > width / Int.three -> canvas.drawColor(deleteColor)
                    else -> canvas.drawColor(editColor)
                }

                val textMargin = resources.getDimension(R.dimen.activity_horizontal_margin)
                    .roundToInt()
                deleteIcon.bounds = Rect(
                    textMargin,
                    viewHolder.itemView.top + textMargin + Int.eight.dp,
                    textMargin + deleteIcon.intrinsicWidth,
                    viewHolder.itemView.top + deleteIcon.intrinsicHeight
                            + textMargin + Int.eight.dp
                )
                editIcon.bounds = Rect(
                    width - textMargin - editIcon.intrinsicWidth,
                    viewHolder.itemView.top + textMargin + Int.eight.dp,
                    width - textMargin,
                    viewHolder.itemView.top + editIcon.intrinsicHeight
                            + textMargin + Int.eight.dp
                )
                if (dX > Int.zero) deleteIcon.draw(canvas) else editIcon.draw(canvas)

                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            private val Int.dp
                get() = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    toFloat(), resources.displayMetrics
                ).roundToInt()
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

    private fun showAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.delete_contact)
        builder.setMessage("${R.string.delete_contact_description} ${Itemcontact.shortName}")
        builder.apply {
            setPositiveButton(R.string.acept) { _, _ ->
                sendViewModel.deleteContact(Itemcontact._id)
                communicator.goTo(SendFragment())
            }
            setNegativeButton(R.string.cancel) { _, _ ->
                communicator.goTo(SendFragment())
            }
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun <T> itemSelected(item: T) {
        communicator.sendData(item, TransferDetailFragment())
    }
}