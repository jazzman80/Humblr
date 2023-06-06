package com.skillboxpractice.humblr.core

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import com.google.android.material.tabs.TabLayout
import com.skillboxpractice.humblr.R

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

@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    val imageLoader = view.context.imageLoader
    val request = ImageRequest.Builder(view.context)
        .data(imageUrl)
        .placeholder(R.drawable.ic_placeholder)
        .target(
            onStart = { placeholder ->
                view.setImageDrawable(placeholder)
            },
            onSuccess = { image ->
                view.setImageDrawable(image)
            }
        )
        .build()
    imageLoader.enqueue(request)
}

@BindingAdapter(value = ["app:onSubscribe", "app:subredditName"], requireAll = true)
fun onSubscribe(view: CheckBox, listener: (String?, Boolean) -> Unit, fullName: String?) {
    view.setOnClickListener {
        listener(fullName, !view.isChecked)
    }
}
