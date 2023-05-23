package com.skillboxpractice.humblr.auth

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.databinding.ActivityAuthBinding
import com.skillboxpractice.humblr.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

// Управляет авторизацией пользователя
// Переход на активити основного приложения

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository
    private lateinit var errorToast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAuthBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.parentActivity = this

        errorToast = Toast.makeText(
            this,
            R.string.auth_error,
            Toast.LENGTH_LONG
        )
        errorToast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)

        handleDeepLink(intent)
    }

    fun authorize() {

        val intent = Intent(
            Intent.ACTION_VIEW,
            repository.composeUrl()
        )
        startActivity(intent)
    }

    private fun handleDeepLink(intent: Intent) {
        if (intent.action != Intent.ACTION_VIEW) return
        val deepLinkUrl = intent.data ?: return
        if (deepLinkUrl.queryParameterNames.contains("error")) {
            errorToast.show()
        } else if (deepLinkUrl.queryParameterNames.contains("code")) {
            val authCode = deepLinkUrl.getQueryParameter("code")
                ?: return

            lifecycleScope.launch {
                val response = repository.getAccessToken(authCode)

                if (response.isSuccessful) {
                    val exitIntent = Intent(this@AuthActivity, MainActivity::class.java)
                    startActivity(exitIntent)
                    finish()

                } else {
                    errorToast.show()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleDeepLink(it) }
    }

}