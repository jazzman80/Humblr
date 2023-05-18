package com.skillboxpractice.humblr.onboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.ActivityOnboardBinding

// Управление страницами онбординга
// Переход на активити авторизации
class OnboardActivity : AppCompatActivity() {

    lateinit var adapter: OnboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityOnboardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboard)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.parentActivity = this

        adapter = OnboardAdapter(this)

    }
}