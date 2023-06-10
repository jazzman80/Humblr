package com.skillboxpractice.humblr.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    val title: LiveData<String> get() = _title
    private val _title = MutableLiveData<String>()

    fun setQuery(query: String) {
        _title.value = " $query"
    }
}