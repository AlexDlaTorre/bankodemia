package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        mSharedPreferences = SharedPreferencesInstance.getInstance(this)

        setContentView(mBinding.root)
    }
}