package com.martini.growing.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.martini.designsystem.utils.UiText
import com.martini.designsystem.utils.text

@Composable
fun ThirdHome() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val someText = UiText.Resource(R.string.app_name).text
        Text(text = "$someText: Third home")
    }
}