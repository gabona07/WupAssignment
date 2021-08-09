package com.smartlynx.wupassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartlynx.wupassignment.R
import com.smartlynx.wupassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        val mainFragment = MainFragment()

        setContentView(view)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, mainFragment).commit()
        }
    }
}