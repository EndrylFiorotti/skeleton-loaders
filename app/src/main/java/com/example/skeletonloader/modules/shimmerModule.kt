package com.example.skeletonloader.modules

import com.example.skeletonloader.viewModel.ShimmerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val shimmerModule = module {
    viewModel {
        ShimmerViewModel()
    }
}