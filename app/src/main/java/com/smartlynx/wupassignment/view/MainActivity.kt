package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.databinding.ActivityMainBinding
import com.smartlynx.wupassignment.model.CardInfo
import com.smartlynx.wupassignment.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Setup main fragment
        val mainFragment = MainFragment()
        setCurrentFragment(mainFragment)
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
}