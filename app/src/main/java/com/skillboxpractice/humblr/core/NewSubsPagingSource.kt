package com.skillboxpractice.humblr.core


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skillboxpractice.humblr.entity.Subreddit
import retrofit2.awaitResponse

class NewSubsPagingSource(
    private val auth: String,
    private val apiService: ApiService,
    private val limit: Int
) : PagingSource<String, Subreddit>() {

    private val before: String? = null
    private val count: Int = 0

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Subreddit> {

        try {
            val result = apiService.getNewSubs(
                auth,
                params.key,
//                before,
//                count,
//                limit
            ).awaitResponse()

            if (result.isSuccessful) {
                return LoadResult.Page(
                    data = result.body()!!.data.children,
                    prevKey = null,
                    nextKey = result.body()!!.data.after
                )
            } else {
                return LoadResult.Error(throw Exception())
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Subreddit>): String? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.data!!.id }
    }
}