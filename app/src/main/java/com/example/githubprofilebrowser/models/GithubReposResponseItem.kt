package com.example.githubprofilebrowser.models

data class GithubReposResponseItem(
    val description: String,
    val forks: Int,
    val name: String,
    val stargazers_count: Int,
    val updated_at: String
)