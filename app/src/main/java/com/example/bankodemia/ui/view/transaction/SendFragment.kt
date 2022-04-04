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
import com.example.bankodemia.ui.view.SearchUser.SearchUserFragment
import com.example.bankodemia.ui.view.TransferDetailFragment
import com.example.bankodemia.ui.viewModel.SendViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_send.*
import kotlin.math.abs
import kotlin.math.roundToInt

class SendFragment() : Fragment(), AdapterItemSelected {

    private var _binding: FragmentSendBinding? = null
    private val binding get() = _binding!!
    private var contact: ContactDeleteDTO? = null
    private lateinit var sendViewModel: SendViewModel
    private lateinit var communicator: FragmentCommunicator
    private lateinit var contactInfo:ContactGetDTO
    private lateinit var Itemcontact: ContactDTO
    private lateinit var  adapter: ContactsAdapter
    private var position: Int = 0


    private lateinit var dragHelper: ItemTouchHelper
    private lateinit var swipeHelper: ItemTouchHelper

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
        adapter = ContactsAdapter(contactsList, isSkeleton, this)

        val swipeGesture = object : SwipeGesture(activity) {
            //BACKGROUND COLOR
            val displayMetrics: DisplayMetrics = resources.displayMetrics
            val height = (displayMetrics.heightPixels / displayMetrics.density).toInt().dp
            val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp

            val deleteIcon = resources.getDrawable(R.drawable.ic_delete, null)
            val archiveIcon = resources.getDrawable(R.drawable.ic_edit, null)
            val rvList = binding.sendRvContacts

            val deleteColor = resources.getColor(android.R.color.holo_red_light)
            val archiveColor = resources.getColor(android.R.color.holo_green_light)
            val sendFragmentLayout = binding.sendFragmentLayout


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                position = viewHolder.adapterPosition
                Itemcontact = contactsList[position]
                when(direction){
                    ItemTouchHelper.LEFT ->{

                        communicator.sendData(Itemcontact,AddContactFragment())

                    }
                    ItemTouchHelper.RIGHT ->{
                        showAlert()
//                        println("POSICION! ${Itemcontact._id}")
////                        contactsList.removeAt(contactDto)
//                        sendViewModel.deleteContact(Itemcontact._id)
////                        setupObservers()
//                        adapter.notifyItemRemoved(position)
                    }
                }

                Snackbar.make(
                    sendFragmentLayout,
                    if (direction == ItemTouchHelper.RIGHT) "Deleted" else "Archived",
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
                //1. Background color based upon direction swiped
                when {
                    abs(dX) < width / 3 -> canvas.drawColor(Color.GRAY)
                    dX > width / 3 -> canvas.drawColor(deleteColor)
                    else -> canvas.drawColor(archiveColor)
                }

                //2. Printing the icons
                val textMargin = resources.getDimension(R.dimen.activity_horizontal_margin)
                    .roundToInt()
                deleteIcon.bounds = Rect(
                    textMargin,
                    viewHolder.itemView.top + textMargin + 8.dp,
                    textMargin + deleteIcon.intrinsicWidth,
                    viewHolder.itemView.top + deleteIcon.intrinsicHeight
                            + textMargin + 8.dp
                )
                archiveIcon.bounds = Rect(
                    width - textMargin - archiveIcon.intrinsicWidth,
                    viewHolder.itemView.top + textMargin + 8.dp,
                    width - textMargin,
                    viewHolder.itemView.top + archiveIcon.intrinsicHeight
                            + textMargin + 8.dp
                )

                //3. Drawing icon based upon direction swiped
                if (dX > 0) deleteIcon.draw(canvas) else archiveIcon.draw(canvas)

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

            //BACKGROUND COLOR
//            TODO DESCOMENTAR
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
////                val position = viewHolder.adapterPosition
//                adapter.notifyItemRemoved(position)
//                val contactDto = contactsList[position]
//                when(direction){
//                    ItemTouchHelper.LEFT ->{
//                        communicator.sendData(contactDto,AddContactFragment())
//
//                    }
//                    ItemTouchHelper.RIGHT ->{
//
//                        println("POSICION! ${contactDto._id}")
//                        sendViewModel.deleteContact(contactDto._id)
////                        setupObservers()
//                    }
//                }
//
//            }


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
        builder.setTitle(R.string.welcome_message)
        builder.setMessage(R.string.deposit_description)
        builder.apply {
            setPositiveButton(R.string.begin) { _, _ ->
                sendViewModel.deleteContact(Itemcontact._id)
//                        setupObservers()
                adapter.notifyItemRemoved(position)
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
//        communicator.sendData(item, AddContactFragment())
    }
}