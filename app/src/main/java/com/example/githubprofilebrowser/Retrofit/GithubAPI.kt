package com.example.githubprofilebrowser.Retrofit

import com.example.githubprofilebrowser.models.GithubReposResponseItem
import com.example.githubprofilebrowser.models.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
//    https://api.github.com/users/{userId}
    @GET("/users/{userId}")
    suspend fun getUserInfo(@Path("userId") username: String): Response<UserInfo>

    @GET("/users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") username: String): Response<List<GithubReposResponseItem>>
}