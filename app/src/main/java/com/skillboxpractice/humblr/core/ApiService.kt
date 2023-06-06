package com.skillboxpractice.humblr.core

import com.skillboxpractice.humblr.entity.Access
import com.skillboxpractice.humblr.entity.Refresh
import com.skillboxpractice.humblr.entity.SubsListing
import com.skillboxpractice.humblr.entity.SubscribeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @POST
    fun getAccessToken(
        @Url url: String,
        @Header("Authorization") auth: String,
        @Query("grant_type") grantType: String,
        @Query("code") authCode: String,
        @Query("redirect_uri") redirectUri: String
    ): Call<Access>

    @POST
    fun refreshToken(
        @Url url: String,
        @Header("Authorization") auth: String,
        @Query("grant_type") grantType: String,
        @Query("refresh_token") refreshToken: String
    ): Call<Refresh>

    @GET("/subreddits/new")
    fun getNewSubs(
        @Header("Authorization") auth: String,
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Call<SubsListing>

    @GET("/subreddits/popular")
    fun getPopularSubs(
        @Header("Authorization") auth: String,
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Call<SubsListing>

    @POST("api/subscribe")
    fun subscribe(
        @Header("Authorization") auth: String,
        @Query("action") action: String?,
        @Query("sr") sr: String?
    ): Call<SubscribeResponse>
}