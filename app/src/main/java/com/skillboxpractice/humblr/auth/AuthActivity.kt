package com.skillboxpractice.humblr.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.ActivityAuthBinding

// Управляет авторизацией пользователя
// Переход на активити основного приложения
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAuthBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth)
        setContentView(binding.root)

    }
}