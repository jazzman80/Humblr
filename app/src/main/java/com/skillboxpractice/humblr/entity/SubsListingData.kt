package com.skillboxpractice.humblr.entity

data class SubsListingData(
    val children: List<Subreddit>,
    val after: String
)