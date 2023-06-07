package com.example.skeletonloader.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.skeletonloader.R
import com.example.skeletonloader.databinding.FragmentLottieBinding
import com.example.skeletonloader.viewModel.LottieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LottieFragment : Fragment(R.layout.fragment_lottie) {
    private lateinit var binding: FragmentLottieBinding
    private val viewModel: LottieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLottieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lottieConfig()
        setUpObservers()
        displayButton()
    }

    private fun setUpObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            layoutVisibility()
        }
    }

    private fun layoutVisibility() {
        with(binding) {
            lottieComponent.apply {
                visibility = View.INVISIBLE
                cancelAnimation()
            }
            btnNext.visibility = View.VISIBLE
        }
    }

    private fun displayButton() {
        Handler().postDelayed({
            kotlin.run {
                viewModel.setLoading()
                buttonClick()
            }
        }, 5000)
    }

    private fun lottieConfig() {
        with(binding) {
            lottieComponent.apply {
                setAnimation("skeleton-loader-kuhan.json")
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        }
    }

    private fun buttonClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.from_shimmerFragment_to_lottieFragment)
        }
    }
}