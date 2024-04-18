package com.martini.growing

import androidx.lifecycle.ViewModel
import com.martini.snackbar.SnackBarDispatcher
import com.martini.snackbar.SnackBarMessage
import com.martini.snackbar.SnackBarType

class MainActivityViewModel(
    private val dispatcher: SnackBarDispatcher = SnackBarDispatcher.shared
) : ViewModel() {

    fun showSnackBar() {
        dispatcher.invoke(MainActivityMessage())
    }
}

data class MainActivityMessage(
    override val id: Int = R.string.app_name,
    override val type: SnackBarType = listOf(SnackBarType.SUCCESS, SnackBarType.ERROR).random()
) : SnackBarMessage