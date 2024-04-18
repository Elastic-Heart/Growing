package com.martini.growing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martini.growing.second.SecondHome
import com.martini.growing.third.ThirdHome
import com.martini.growing.ui.theme.GrowingTheme
import com.martini.snackbar.GrowingSnackBarHost

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
fun GrowingNavigation() {
    val navController = rememberNavController()

    fun onGoToSecondClicked() {
        navController.navigate("SecondHome")
    }

    fun onGoToThirdClicked() {
        navController.navigate("ThirdHome")
    }

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            Home(
                onGoToSecondClicked = ::onGoToSecondClicked,
                onGoToThirdClicked = ::onGoToThirdClicked
            )
        }
        composable("SecondHome") { SecondHome() }
        composable("ThirdHome") { ThirdHome() }
    }
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    mainViewModel: MainActivityViewModel = viewModel(),
    onGoToSecondClicked: () -> Unit,
    onGoToThirdClicked: () -> Unit
) {
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
            Button(onClick = { onGoToSecondClicked() }) {
                Text(text = "Second screen")
            }
            Button(onClick = { onGoToThirdClicked() }) {
                Text(text = "Third screen")
            }
            Button(onClick = mainViewModel::showSnackBar) {
                Text(text = "Show snackbar")
            }
        }
    }
}

@Composable
fun ScalableImage() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    ) {

        var zoom by remember {
            mutableFloatStateOf(1f)
        }

        var offset by remember {
            mutableStateOf(Offset.Zero)
        }

        val state = rememberTransformableState { zoomChange, panChange, _ ->
            zoom = (zoom * zoomChange).coerceIn(1f, 5f)

            val scaledX  = (zoom - 1) * constraints.maxWidth
            val scaledY  = (zoom - 1) * constraints.maxHeight

            val maxX = scaledX / 2
            val maxY = scaledY / 2

            val scaledPanChangeX = zoom * panChange.x
            val scaledPanChangeY = zoom * panChange.y

            offset = Offset(
                x = (offset.x + scaledPanChangeX).coerceIn(-maxX, maxX),
                y = (offset.y + scaledPanChangeY).coerceIn(-maxY, maxY)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.landscape),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
                .graphicsLayer {
                    scaleX = zoom
                    scaleY = zoom
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state)
        )
    }
}