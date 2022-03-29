package com.example.bankodemia.ui.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.showMessage
import com.example.bankodemia.core.showSnackBarMessagee
import com.example.bankodemia.core.utils.CONTACTDATA
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.TRANSACTIONDETAIL
import com.example.bankodemia.core.utils.quotes
import com.example.bankodemia.core.zero
import com.example.bankodemia.databinding.ActivityHome2Binding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.loader.*
import kotlin.random.Random

class HomeActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityHome2Binding
    private var handler: Handler = Handler()
    var runnable: Runnable? = null
    val delay = 1800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.homeToolBar))
        supportActionBar?.hide()
        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_home2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.servicesFragment, R.id.cardsFragment, R.id.sendFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun <T> sendData(data: T, destination: Fragment) {
        val bundle = Bundle()
        when (data) {
            is TransactionDTO -> {
                bundle.putSerializable(TRANSACTIONDETAIL, data as TransactionDTO)
            }
            is ContactDTO -> {
                bundle.putSerializable(CONTACTDATA, data as ContactDTO)
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        destination.arguments = bundle

        transaction.replace(R.id.nav_host_fragment_activity_home2, destination)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun goTo(destination: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_home2, destination)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.commit()
    }

    override fun showLoader(isVisible: Boolean) {
        if (isVisible) {
            binding.apply {
                loaderView.visibility = View.VISIBLE
                animationView.playAnimation()
            }
        } else {
            binding.apply {
                loaderView.visibility = View.GONE
                animationView.cancelAnimation()
            }
        }
    }

    override fun showToastMessage(message: String) {
        showSnackBarMessagee(message, Snackbar.LENGTH_LONG)
    }
}