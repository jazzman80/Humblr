package com.skillboxpractice.humblr.auth

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.databinding.ActivityAuthBinding
import com.skillboxpractice.humblr.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthorizationService
import javax.inject.Inject

// Управляет авторизацией пользователя
// Переход на активити основного приложения

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAuthBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.parentActivity = this
    }

    fun authorize() {

        val authService = AuthorizationService(this)

        authService.performAuthorizationRequest(
            repository.authRequest(),
            PendingIntent.getActivity(
                this,
                0,
                Intent(this, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        )

        finish()
    }
}