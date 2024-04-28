package com.martini.growing.networking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

val networking = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

@Composable
fun rememberNetworking() = remember {
    networking
}