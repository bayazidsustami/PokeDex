package com.example.pokedex.ui.activity

import android.os.Bundle
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvTest.text = "okok"
    }

}