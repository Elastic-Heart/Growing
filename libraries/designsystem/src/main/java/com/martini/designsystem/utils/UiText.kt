package com.martini.designsystem.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource

sealed interface UiText {

    @JvmInline
    value class Text(val value: String) : UiText

    @Immutable
    data class Resource(
        @StringRes internal val id: Int,
        internal val args: List<Any> = emptyList()
    ) : UiText

}

val UiText.text
    @Composable
    get() = when (this) {
        is UiText.Text -> value
        is UiText.Resource -> stringResource(id = id, args.toTypedArray())
    }