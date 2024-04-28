package com.martini.growing.second.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.martini.growing.networking.rememberNetworking
import com.martini.growing.second.posts.data.PostModel
import io.ktor.client.call.body
import io.ktor.client.request.get

sealed interface PostState {
    data object Idle : PostState
    data object Loading : PostState

    @Immutable

    data class Loaded(
        val posts: List<PostModel>
    ) : PostState

    data object Error : PostState
}

@Composable
fun PostsScreen() {
    val networking = rememberNetworking()

    var state by remember { mutableStateOf<PostState>(PostState.Idle) }

    LaunchedEffect(Unit) {
        state = PostState.Loading

        state = try {
            val response: List<PostModel> =
                networking.get("https://jsonplaceholder.typicode.com/posts").body()
            PostState.Loaded(response)
        } catch (e: Exception) {
            PostState.Error
        }
    }

    PostList(state = state)
}

@Composable
internal fun PostList(
    state: PostState
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            when (state) {
                is PostState.Idle, PostState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is PostState.Error -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Something went wrong!")
                        }
                    }
                }

                is PostState.Loaded -> {
                    items(
                        items = state.posts,
                        key = { post -> post.id }
                    ) { post ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = post.title,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            supportingContent = { Text(text = post.body) }
                        )
                    }
                }
            }
        }
    }
}