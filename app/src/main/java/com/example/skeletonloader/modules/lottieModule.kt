package com.example.skeletonloader.modules

import com.example.skeletonloader.viewModel.LottieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lottieModule = module {
    viewModel {
        LottieViewModel()
    }
}