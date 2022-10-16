package com.geekbrains.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.material.databinding.MainActivityBinding
import com.geekbrains.material.view.EarthFragment
import com.geekbrains.material.view.MarsFragment
import com.geekbrains.material.view.ViewPagerFragment
import com.geekbrains.material.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_bottom_navigation_POD -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ViewPagerFragment()).commit()
                        true
                    }
                    R.id.action_bottom_navigation_earth -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, EarthFragment()).commit()
                        true
                    }
                    R.id.action_bottom_navigation_mars -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, MarsFragment()).commit()
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
            selectedItemId = R.id.action_bottom_navigation_POD
        }
    }
}