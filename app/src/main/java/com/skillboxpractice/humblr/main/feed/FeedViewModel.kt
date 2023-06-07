package com.skillboxpractice.humblr.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.skillboxpractice.humblr.core.LoadingState
import com.skillboxpractice.humblr.core.Repository
import com.skillboxpractice.humblr.entity.SubscribeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(), FeedAdapter.ParentViewModel {
    private val newSubsFlow = repository.getNewSubs().flow.cachedIn(viewModelScope)
    private val popularSubsFlow = repository.getPopularSubs().flow.cachedIn(viewModelScope)

    val subsAdapter = FeedAdapter(this)

    val error: LiveData<Boolean> get() = _error
    private val _error = MutableLiveData(false)

    val navigateToSearch: StateFlow<Boolean> get() = _navigateToSearch
    private val _navigateToSearch = MutableSharedFlow(0, false)

    val loadingState: LiveData<LoadingState> get() = _loadingState
    private val _loadingState = MutableLiveData(LoadingState.LOADING)

    val onTabSelected = fun(position: Int) {
        viewModelScope.launch {
            if (position == 0) {
                newSubsFlow.collectLatest {
                    subsAdapter.submitData(it)
                }
            } else {
                popularSubsFlow.collectLatest {
                    subsAdapter.submitData(it)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.refreshToken()

            newSubsFlow.collectLatest {
                subsAdapter.submitData(it)
            }
        }

        viewModelScope.launch {
            subsAdapter.loadStateFlow.collectLatest { loadState ->
                if (loadState.refresh is LoadState.Loading) _loadingState.value =
                    LoadingState.LOADING
                else if (loadState.refresh is LoadState.Error) _loadingState.value =
                    LoadingState.ERROR
                else if (loadState.refresh is LoadState.NotLoading) _loadingState.value =
                    LoadingState.SUCCESS
            }
        }
    }

    override val onSubscribeClick = fun(fullName: String?, isSubscribed: Boolean) {

        viewModelScope.launch {
            try {
                val result: Response<SubscribeResponse> =
                    if (isSubscribed) repository.unsubscribe(fullName)
                    else repository.subscribe(fullName)

                if (result.isSuccessful) {
                    subsAdapter.refresh()
                } else {
                    _error.value = true
                }
            } catch (e: Exception) {
                _error.value = true
            }
        }

    }

    val onSearch = fun() {
        _navigateToSearch.value = true
        //_navigateToSearch.value = false
    }

}