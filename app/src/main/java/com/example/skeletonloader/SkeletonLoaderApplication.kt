package com.example.skeletonloader

import android.app.Application
import com.example.skeletonloader.modules.lottieModule
import com.example.skeletonloader.modules.shimmerModule
import com.example.skeletonloader.modules.skeletonLoaderModule
import org.koin.core.context.startKoin

class SkeletonLoaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            modules(skeletonLoaderModule, shimmerModule, lottieModule)
        }
    }
}