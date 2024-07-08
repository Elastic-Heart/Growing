package com.martini.growing.di

import com.martini.growing.MainActivityViewModel
import com.martini.growing.ndk.AppNdkManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val AppModule = module {
    factory { AppNdkManager() }
    viewModel { MainActivityViewModel(get(), get(), get()) }
}