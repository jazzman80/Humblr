package com.skillboxpractice.humblr.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.FragmentOnboard1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardFragment1 : Fragment() {

    private var _binding: FragmentOnboard1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard_1, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}