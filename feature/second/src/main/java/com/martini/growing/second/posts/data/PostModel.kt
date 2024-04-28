package com.martini.growing.second.posts.data

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)
