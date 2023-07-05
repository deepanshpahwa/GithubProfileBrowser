package com.example.githubprofilebrowser.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubprofilebrowser.R
import com.example.githubprofilebrowser.adapter.RecyclerViewAdapter
import com.example.githubprofilebrowser.Retrofit.Resource
import com.example.githubprofilebrowser.ViewModels.*
import com.example.githubprofilebrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity.k"
    private lateinit var binding: ActivityMainBinding
    private var viewManager = LinearLayoutManager(this)
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    //    private lateinit var githubReposViewModel: GithubReposViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialiseAdapter()
        val repository = Repository()
        val userInfoViewModelProviderFactory = UserInfoViewModelProviderFactory(repository)
        userViewModel = ViewModelProvider(
            this,
            userInfoViewModelProviderFactory
        ).get(UserViewModel::class.java)
        val application = requireNotNull(this).application

        userViewModel.userInfoResponseItem.observe(this, Observer { response ->
            when (response) {
                is Resource.Success<*> -> {
//                    hideProgressBar()
                    response.data?.let { userInfoResponse ->
//                        binding.imageView
                        Glide.with(this)
                            .load(userInfoResponse.avatar_url)
                            .into(binding.imageView)
                    }
                }

                is Resource.Error<*> -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error has occured: $message")
                    }
                }

                is Resource.Loading<*> -> {
//                    showProgressBar()
                }
            }
        })

        userViewModel.githubReposResponseItem.observe(this, Observer { response ->
            when (response) {
                is Resource.Success<*> -> {
//                    hideProgressBar()
                    response.data?.let { githubRepoResponse ->
                        recyclerViewAdapter.differ.submitList(githubRepoResponse)
                    }
                }

                is Resource.Error<*> -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error has occured: $message")
                    }
                }

                is Resource.Loading<*> -> {
//                    showProgressBar()
                }
            }
        })
        binding.searchButton.setOnClickListener{
            Log.e(TAG, "onCreate: ::::::::: "+ binding.searchBar.text.toString())
            userViewModel.getUserInfo(binding.searchBar.text.toString())
            userViewModel.getGithubRepos(binding.searchBar.text.toString())
        }

        recyclerViewAdapter.setOnItemClickListener {View.OnClickListener(){
            val bundle = Bundle().apply {
                putSerializable("repoinfo", userViewModel)
            }
            startActivity(Intent(this, RepoInformation::class.java).putExtras(bundle))

        } }


    }


    private fun hideProgressBar() {
        TODO("Not yet implemented")
    }

    private fun showProgressBar() {
        TODO("Not yet implemented")
    }


    private fun initialiseAdapter(){
        recyclerViewAdapter = RecyclerViewAdapter()

        binding.recyclerView.apply {
            adapter=recyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}