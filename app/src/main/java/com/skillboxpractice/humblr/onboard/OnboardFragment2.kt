package com.skillboxpractice.humblr.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.FragmentOnboard2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardFragment2 : Fragment() {

    private var _binding: FragmentOnboard2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard_2, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}