package com.skillboxpractice.humblr.core

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter("app:onTabSelected")
fun onTabSelected(tabLayout: TabLayout, listener: (Int) -> Unit) {
    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            listener(tab?.position!!)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    })
}

@BindingAdapter("app:visibleOnNew")
fun visibleOnNew(view: View, listType: SubListType) {
    if (listType == SubListType.NEW) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:visibleOnPopular")
fun visibleOnPopular(view: View, listType: SubListType) {
    if (listType == SubListType.POPULAR) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:visibleIf")
fun visibleIf(view: View, value: Boolean) {
    if (value) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}
