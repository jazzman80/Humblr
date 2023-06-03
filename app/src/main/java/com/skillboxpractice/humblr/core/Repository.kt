package com.skillboxpractice.humblr.core

import android.net.Uri
import androidx.paging.Pager
import com.skillboxpractice.humblr.entity.Access
import com.skillboxpractice.humblr.entity.Subreddit
import retrofit2.Response

interface Repository {

    val accessToken: String
    val isOnboardDone: Boolean
    fun onboardDone()
    fun composeUrl(): Uri?
    suspend fun getAccessToken(authCode: String): Response<Access>
    fun getNewSubs(): Pager<String, Subreddit>
    suspend fun refreshToken()
    fun getPopularSubs(): Pager<String, Subreddit>
    fun searchSubs(): Pager<String, Subreddit>

}