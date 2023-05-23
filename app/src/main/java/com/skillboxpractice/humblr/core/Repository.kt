package com.skillboxpractice.humblr.core

import android.net.Uri
import com.skillboxpractice.humblr.entity.Access
import retrofit2.Response

interface Repository {

    val accessToken: String
    val isOnboardDone: Boolean
    fun onboardDone()
    fun composeUrl(): Uri?
    suspend fun getAccessToken(authCode: String): Response<Access>

}