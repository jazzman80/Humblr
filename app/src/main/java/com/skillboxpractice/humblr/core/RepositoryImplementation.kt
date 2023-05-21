package com.skillboxpractice.humblr.core

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    @ApplicationContext private val appContext: Context
) : Repository {

    private val preferenceFileKey = "PREFERENCE_FILE_KEY"
    private val onboardKey = "ONBOARD_KEY"
    private val authUri = "https://www.reddit.com/api/v1/authorize"
    private val tokenUri = "https://www.reddit.com/api/v1/access_token"
    private val clientId = "IktEA1UocDhvVVrO7VHScw"
    private val responseType = ResponseTypeValues.CODE
    private var state = ""
    private val redirectUri = "com.skillboxpractice.humblr://humblr"
    private val scope = ""

    private var _isOnboardDone = false

    init {

        // Загружаем состояние онбординга
        val sharedPreferences =
            appContext.getSharedPreferences(preferenceFileKey, AppCompatActivity.MODE_PRIVATE)
        _isOnboardDone = sharedPreferences.getBoolean(onboardKey, false)
    }

    override fun isOnboardDone(): Boolean {
        return _isOnboardDone
    }

    override fun onboardDone() {
        _isOnboardDone = true

        // Сохраняем состояние онбординга
        val edit =
            appContext.getSharedPreferences(
                preferenceFileKey,
                AppCompatActivity.MODE_PRIVATE
            ).edit()
        edit.putBoolean(onboardKey, _isOnboardDone).apply()
        edit.clear()
    }

    override fun authRequest(): AuthorizationRequest {
        val serviceConfig = AuthorizationServiceConfiguration(
            Uri.parse(authUri),
            Uri.parse(tokenUri)
        )

        val authRequestBuilder = AuthorizationRequest.Builder(
            serviceConfig,
            clientId,
            responseType,
            Uri.parse(redirectUri)
        )

        return authRequestBuilder
            .setScope(scope)
            .build()
    }

}