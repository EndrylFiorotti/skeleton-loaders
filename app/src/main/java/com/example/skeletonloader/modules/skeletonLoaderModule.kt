package com.example.skeletonloader.modules

import com.example.skeletonloader.viewModel.SkeletonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val skeletonLoaderModule = module {
    viewModel {
        SkeletonViewModel()
    }
}