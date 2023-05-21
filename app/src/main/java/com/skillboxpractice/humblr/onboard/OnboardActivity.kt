package com.skillboxpractice.humblr.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayoutMediator
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.auth.AuthActivity
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.databinding.ActivityOnboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Управление страницами онбординга
// Переход на активити авторизации

@AndroidEntryPoint
class OnboardActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    private lateinit var binding: ActivityOnboardBinding
    val selectedTab: LiveData<Int> get() = _selectedTab
    private val _selectedTab = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_onboard)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.parentActivity = this

        binding.viewPager.adapter = OnboardAdapter(this)
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { _, _ -> }.attach()

    }

    fun navigateToAuth() {

        repository.onboardDone()

        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    val onTabSelected = fun(position: Int) {
        _selectedTab.value = position
    }
}