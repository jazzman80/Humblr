package com.skillboxpractice.humblr.core

import net.openid.appauth.AuthorizationRequest

interface Repository {

    fun isOnboardDone(): Boolean
    fun onboardDone()
    fun authRequest(): AuthorizationRequest

}