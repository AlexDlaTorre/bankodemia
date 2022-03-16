package com.example.bankodemia.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankodemia.databinding.ActivityMainBinding
import com.example.bankodemia.core.transitionFragment
import com.example.bankodemia.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            mainBtnLogin.setOnClickListener {
                transitionFragment(fragmentClass = HomeFragment(), supportFragmentManager)
            }

            mainBtnCreateAccount.setOnClickListener {
                transitionFragment(fragmentClass = CreateAccountFragment(), supportFragmentManager)
            }
        }
    }
}