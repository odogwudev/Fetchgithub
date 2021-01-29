package com.odogwudev.fetchgithub.api

import com.odogwudev.fetchgithub.model.UserFollowResponse
import com.odogwudev.fetchgithub.model.UserProfileResponse
import com.odogwudev.fetchgithub.model.UserRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyGitHubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users/{userName}")
    suspend fun getUserProfile(
        @Path("userName") userName: String
    ): Response<UserProfileResponse>

    @GET("users/{userName}/followers")
    suspend fun getUserFollowers(
        @Path("userName") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): UserFollowResponse

    @GET("users/{userName}/following")
    suspend fun getUserFollowings(
        @Path("userName") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): UserFollowResponse

    @GET("users/{userName}/repos")
    suspend fun getUserRepo(
        @Path("userName") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): UserRepoResponse
}