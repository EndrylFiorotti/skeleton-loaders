package com.example.skeletonloader.view.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skeletonloader.R
import com.example.skeletonloader.databinding.FragmentSkeletonBinding
import com.example.skeletonloader.viewModel.SkeletonViewModel
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class SkeletonFragment : Fragment(R.layout.fragment_skeleton) {
    private lateinit var binding: FragmentSkeletonBinding
    private val viewModel: SkeletonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSkeletonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSkeletonAnimation(binding.skeletonComponent)
        binding.toolbar.root.title = "Personal Skeleton"
        setUpObservers()
        displayButton()
    }

    private fun displayButton() {
        Handler().postDelayed({
            kotlin.run {
                viewModel.setLoading()
                buttonClick()
            }
        }, 5000)
    }

    private fun setUpObservers() {
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                layoutVisibility()
            }
        }
    }

    private fun layoutVisibility() {
        with(binding) {
            skeletonComponent.visibility = View.INVISIBLE
            btnNext.visibility = View.VISIBLE
        }
    }

    private fun buttonClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.from_skeletonFragment_to_shimmerFragment)
        }
    }

    private fun startSkeletonAnimation(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0.5F, 1.0F).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}