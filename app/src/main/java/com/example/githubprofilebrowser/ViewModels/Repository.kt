package com.example.githubprofilebrowser.ViewModels

import com.example.githubprofilebrowser.Retrofit.RetrofitInstance

class Repository {

    suspend fun getGithubRepos (username: String) = RetrofitInstance.api.getUserRepos(username)
    suspend fun getUserInfo (username:String) = RetrofitInstance.api.getUserInfo(username)

}
