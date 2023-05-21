package com.skillboxpractice.humblr.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skillboxpractice.humblr.auth.AuthActivity
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.onboard.OnboardActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Стартовая активити - загружает основные компоненты и управляет дальнейшим переходом
// При первом запуске - переход на онбординг OnboardActivity
// Если онбординг пройден и не существует валидного токена - переход на авторизацию AuthActivity
// Если есть валидный токкен - переход в основное приложение - MainActivity

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent: Intent = if (repository.isOnboardDone()) {
            Intent(this, AuthActivity::class.java)
        } else {
            Intent(this, OnboardActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}