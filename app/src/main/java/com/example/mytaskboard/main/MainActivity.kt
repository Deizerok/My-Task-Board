package com.example.mytaskboard.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mytaskboard.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.lang.Thread.sleep
import java.util.logging.Handler

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.navigationLiveData().observe(this) {
            it.show(binding.container.id, supportFragmentManager)
        }

        viewModel.init(savedInstanceState == null)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() = Unit
}