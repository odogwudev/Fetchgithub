package com.odogwudev.fetchgithub.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.odogwudev.fetchgithub.api.MyGitHubApi
import com.odogwudev.fetchgithub.model.UserRepoResponse
import retrofit2.HttpException
import java.io.IOException

private const val REPO_STARTING_PAGE_INDEX = 1

class RepoPagingSource(
    private val myGitHubApi: MyGitHubApi,
    private val query: String
) : PagingSource<Int, UserRepoResponse.UserRepoResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRepoResponse.UserRepoResponseItem> {
        val position = params.key ?: REPO_STARTING_PAGE_INDEX

        return try {
            val response = myGitHubApi.getUserRepo(query, perPage = 30, page = position)
            Log.d("fuck", response.size.toString())
            LoadResult.Page(
                data = response,
                prevKey = if (position == REPO_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}