package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.bankodemia.R
import com.example.bankodemia.core.showSnackBarMessagee
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.TRANSACTIONDETAIL
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.databinding.ActivityMainBinding
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.loader.*


class MainActivity : AppCompatActivity(), FragmentCommunicator {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mSharedPreferences = SharedPreferencesInstance.getInstance(this)
        mSharedPreferences.clean()
        setContentView(mBinding.root)
    }

    override fun <T> sendData(data: T, destination: Fragment) {
        val bundle = Bundle()
        when (data) {
            is AuthDTO -> {
                bundle.putSerializable(TRANSACTIONDETAIL, data as AuthDTO)
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        destination.arguments = bundle

        transaction.replace(R.id.nav_host_fragment_activity_main, destination)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun goTo(destination: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, destination)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.commit()
    }

    override fun showLoader(isVisible: Boolean) {
        if (isVisible) {
            mBinding.apply {
                loaderView.visibility = View.VISIBLE
                animationView.playAnimation()
            }
        } else {
            mBinding.apply {
                loaderView.visibility = View.GONE
                animationView.cancelAnimation()
            }
        }
    }

    override fun showToastMessage(message: String) {
        showSnackBarMessagee(message, Snackbar.LENGTH_LONG)
    }
}