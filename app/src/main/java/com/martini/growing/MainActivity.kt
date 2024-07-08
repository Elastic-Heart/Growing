package com.martini.growing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martini.designsystem.components.buttons.GrowingButton
import com.martini.designsystem.components.buttons.GrowingTextButton
import com.martini.designsystem.components.snackbar.GrowingSnackBarHost
import com.martini.designsystem.theme.GrowingTheme
import com.martini.growing.second.posts.PostsScreen
import com.martini.growing.state.MainUiState
import com.martini.growing.third.ThirdHome
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GrowingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GrowingNavigation()
                }
            }
        }
    }
}

@Composable
fun GrowingNavigation(
    mainViewModel: MainActivityViewModel = koinViewModel()
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

    val navController = rememberNavController()

    fun onGoToSecondClicked() {
        navController.navigate("SecondHome")
    }

    fun onGoToThirdClicked() {
        navController.navigate("ThirdHome")
    }

    AnimatedVisibility(visible = uiState is MainUiState.Loading) {
        GrowingTheme {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    AnimatedVisibility(visible = uiState !is MainUiState.Loading) {
        GrowingTheme {
            NavHost(navController = navController, startDestination = "Home") {
                composable("Home") {
                    Home(
                        onGoToSecondClicked = ::onGoToSecondClicked,
                        onGoToThirdClicked = ::onGoToThirdClicked
                    )
                }
                composable("SecondHome") { PostsScreen() }
                composable("ThirdHome") { ThirdHome() }
            }
        }
    }
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    mainViewModel: MainActivityViewModel = koinViewModel(),
    onGoToSecondClicked: () -> Unit,
    onGoToThirdClicked: () -> Unit
) {
    val showSnackBarEnabled by mainViewModel.showSnackBarEnabled.collectAsStateWithLifecycle()
    val messageFromNative by mainViewModel.messageFromNative.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = { GrowingSnackBarHost() }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GrowingButton(onClick = { onGoToSecondClicked() }) {
                Text(text = "Second screen")
            }
            GrowingButton(onClick = { onGoToThirdClicked() }) {
                Text(text = "Third screen")
            }
            AnimatedVisibility(visible = showSnackBarEnabled) {
                GrowingTextButton(onClick = mainViewModel::showSnackBar) {
                    Text(text = "Show snackbar")
                }
            }
            Text(
                text = messageFromNative,
                style = GrowingTheme.typography.body
            )
        }
    }
}