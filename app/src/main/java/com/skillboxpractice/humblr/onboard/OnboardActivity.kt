package com.skillboxpractice.humblr.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.auth.AuthActivity
import com.skillboxpractice.humblr.databinding.ActivityOnboardBinding

// Управление страницами онбординга
// Переход на активити авторизации
class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboard)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.parentActivity = this

        binding.viewPager.adapter = OnboardAdapter(this)

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { _, _ -> }.attach()

    }

    fun navigateToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}