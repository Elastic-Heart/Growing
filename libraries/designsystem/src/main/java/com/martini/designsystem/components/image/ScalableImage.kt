package com.martini.designsystem.components.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.martini.designsystem.R
import com.martini.designsystem.theme.GrowingTheme

@Composable
fun ScalableImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int
) {
    BoxWithConstraints(
        modifier = modifier
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
            painter = painterResource(id = imageId),
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

@Composable
@Preview(showBackground = true)
private fun ScalableImagePreview() {
    GrowingTheme {
        ScalableImage(
            modifier = Modifier.fillMaxSize(),
            imageId = R.drawable.landscape
        )
    }
}