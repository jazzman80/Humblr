package com.skillboxpractice.humblr.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.core.SubListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val newSubsFlow = repository.getNewSubs().flow.cachedIn(viewModelScope)

    val newSubsAdapter = FeedAdapter()

    val subsListType: LiveData<SubListType> get() = _subsListType
    private val _subsListType = MutableLiveData(SubListType.NEW)

    val onTabSelected = fun(position: Int) {
        if (position == 0) _subsListType.value = SubListType.NEW
        else _subsListType.value = SubListType.POPULAR
    }

    init {
        viewModelScope.launch {
            repository.refreshToken()

            newSubsFlow.collectLatest { pagingData ->
                newSubsAdapter.submitData(pagingData)
            }
        }
    }

//        viewModelScope.launch {
//            newSubsAdapter.loadStateFlow.collectLatest { loadState ->
//                if (loadState.refresh is LoadState.Loading) _loadingState.value =
//                    LoadingState.LOADING
//                else if (loadState.refresh is LoadState.Error) _loadingState.value =
//                    LoadingState.ERROR
//                else if (loadState.refresh is LoadState.NotLoading) _loadingState.value =
//                    LoadingState.SUCCESS
//            }
//        }


}