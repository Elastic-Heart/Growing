package com.martini.growing.di

import com.martini.growing.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val AppModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
}