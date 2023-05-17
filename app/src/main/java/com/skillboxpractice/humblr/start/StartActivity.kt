package com.skillboxpractice.humblr.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skillboxpractice.humblr.onboard.OnboardActivity

// Стартовая активити - загружает основные компоненты и управляет дальнейшим переходом
// При первом запуске - переход на онбординг OnboardActivity
// Если онбординг пройден и не существует валидного токена - переход на авторизацию AuthActivity
// Если есть валидный токкен - переход в основное приложение - MainActivity
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, OnboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}