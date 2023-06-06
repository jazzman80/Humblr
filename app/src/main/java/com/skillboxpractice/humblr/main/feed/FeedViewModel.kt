package com.skillboxpractice.humblr.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.core.SubListType
import com.skillboxpractice.humblr.entity.SubscribeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(), FeedAdapter.ParentViewModel {
    private val newSubsFlow = repository.getNewSubs().flow.cachedIn(viewModelScope)

    val newSubsAdapter = FeedAdapter(this)

    val subsListType: LiveData<SubListType> get() = _subsListType
    private val _subsListType = MutableLiveData(SubListType.NEW)

    val error: LiveData<Boolean> get() = _error
    private val _error = MutableLiveData(false)

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

    override val onSubscribeClick = fun(fullName: String?, isSubscribed: Boolean) {

        viewModelScope.launch {
            try {
                val result: Response<SubscribeResponse> =
                    if (isSubscribed) repository.unsubscribe(fullName)
                    else repository.subscribe(fullName)

                if (!result.isSuccessful) _error.value = true
            } catch (e: Exception) {
                _error.value = true
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