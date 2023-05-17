package com.skillboxpractice.humblr.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // Основное приложение
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

    }
}