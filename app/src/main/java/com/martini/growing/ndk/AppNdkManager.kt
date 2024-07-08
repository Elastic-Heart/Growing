package com.martini.growing.ndk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppNdkManager {

    companion object {
        init {
            System.loadLibrary("growing")
        }
    }

    private external fun helloFromCppNative(): String

    suspend fun helloFromCpp(): String {
        return helloFromCppNative()
    }

}