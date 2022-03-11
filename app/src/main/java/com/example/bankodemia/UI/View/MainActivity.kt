package com.example.bankodemia.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bankodemia.R
import com.example.bankodemia.UI.View.home.HomeActivity
import com.example.bankodemia.core.transitionFragment
import com.example.bankodemia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        boton.setOnClickListener {
            startActivity(intent24)
        }

        with(binding) {
            mainBtnLogin.setOnClickListener {
                transitionFragment(fragmentClass = LoginFragment(), supportFragmentManager)
            }

            mainBtnCreateAccount.setOnClickListener {
                transitionFragment(fragmentClass = CreateAccountFragment(), supportFragmentManager)
            }
        }
    }
    val boton: Button = findViewById(R.id.button)

    val intent24 = Intent (this, HomeActivity::class.java)


}