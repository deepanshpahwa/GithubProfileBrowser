package com.example.githubprofilebrowser.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubprofilebrowser.Retrofit.Resource
import com.example.githubprofilebrowser.models.GithubReposResponseItem
import com.example.githubprofilebrowser.models.UserInfo
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.Serializable

class UserViewModel(val repository: Repository) : ViewModel(), Serializable {

    val githubReposResponseItem: MutableLiveData<Resource<List<GithubReposResponseItem>>> = MutableLiveData()
    val userInfoResponseItem: MutableLiveData<Resource<UserInfo>> = MutableLiveData()

    init {
//        getUserInfo(username)
//        getGithubRepos(username)//TODO
    }

    fun getUserInfo (username: String) = viewModelScope.launch {
        userInfoResponseItem.postValue(Resource.Loading())
        val response = repository.getUserInfo(username)
        Log.e("RETROFIT",response.message()+response.body())
        userInfoResponseItem.postValue(handleUserInfoResponse(response))
    }

    fun handleUserInfoResponse(response: Response<UserInfo>): Resource<UserInfo>? {
        if (response.isSuccessful){
            response.body()?. let{resultResponse -> return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getGithubRepos (username: String) = viewModelScope.launch {
        githubReposResponseItem.postValue(Resource.Loading())
        val response = repository.getGithubRepos(username)
        Log.e("RETROFIT",response.message()+response.body())
        githubReposResponseItem.postValue(handleGithubReposResponse(response))
    }

    fun handleGithubReposResponse(response: Response<List<GithubReposResponseItem>>): Resource<List<GithubReposResponseItem>>? {
        if (response.isSuccessful){
            response.body()?. let{resultResponse -> return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
