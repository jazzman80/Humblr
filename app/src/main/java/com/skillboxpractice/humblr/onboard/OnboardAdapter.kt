package com.skillboxpractice.humblr.onboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardAdapter(parentActivity: OnboardActivity): FragmentStateAdapter(parentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnboardFragment1()
            1 -> OnboardFragment2()
            else -> OnboardFragment3()
        }
    }
}