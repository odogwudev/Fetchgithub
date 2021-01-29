package com.odogwudev.fetchgithub.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.odogwudev.fetchgithub.api.MyGitHubApi
import com.odogwudev.fetchgithub.model.UserProfileResponse
import com.odogwudev.fetchgithub.pagingsource.FollowersPagingSource
import com.odogwudev.fetchgithub.pagingsource.FollowingsPagingSource
import com.odogwudev.fetchgithub.pagingsource.RepoPagingSource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val myGitHubApi: MyGitHubApi) {

    suspend fun getUserProfile(userName: String): Response<UserProfileResponse> {
        return myGitHubApi.getUserProfile(userName)
    }

    fun getUserFollowers(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FollowersPagingSource(
                    myGitHubApi, query
                )
            }
        ).liveData

    fun getUserFollowings(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FollowingsPagingSource(
                    myGitHubApi, query
                )
            }
        ).liveData


    fun getUserRepo(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RepoPagingSource(
                    myGitHubApi, query
                )
            }
        ).liveData
}