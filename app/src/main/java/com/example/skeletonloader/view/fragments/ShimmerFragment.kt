package com.example.skeletonloader.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skeletonloader.R
import com.example.skeletonloader.databinding.FragmentShimmerBinding
import com.example.skeletonloader.viewModel.ShimmerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShimmerFragment : Fragment(R.layout.fragment_shimmer) {
    private lateinit var binding: FragmentShimmerBinding
    private val viewModel: ShimmerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentShimmerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.root.title = "Shimmer"
        binding.shimmerComponent.startShimmer()
        setUpObservers()
        displayButton()
    }

    private fun setUpObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            layoutVisibility()
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

    private fun layoutVisibility() {
        with(binding) {
            shimmerComponent.apply {
                visibility = View.INVISIBLE
                stopShimmer()
            }
            btnNext.visibility = View.VISIBLE
        }
    }

    private fun buttonClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.from_shimmerFragment_to_lottieFragment)
        }
    }
}