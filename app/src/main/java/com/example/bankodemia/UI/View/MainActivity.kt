package com.example.bankodemia.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bankodemia.R
import com.example.bankodemia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBtnCreateAccount.setOnClickListener {
            val createAccountFragment  = CreateAccountFragment()
            val fragment: Fragment? =
                supportFragmentManager.findFragmentByTag(CreateAccountFragment::class.java.simpleName)

            if(fragment !is CreateAccountFragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.pruebaLayout,createAccountFragment,CreateAccountFragment::class.java.simpleName)
                    .commit()
            }
        }


        binding.mainBtnLogin.setOnClickListener {
            val createAccountFragment = LoginFragment()
            val fragment: Fragment? =
                supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

            if (fragment !is LoginFragment) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.pruebaLayout, createAccountFragment, LoginFragment::class.java.simpleName)
                    .commit()
            }

            binding.pruebaLayout.visibility = View.VISIBLE
            binding.pruebaLayoutCerrar.visibility = View.GONE
        }
    }
}