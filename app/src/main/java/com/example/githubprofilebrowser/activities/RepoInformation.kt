package com.example.githubprofilebrowser.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.githubprofilebrowser.R
//import com.example.githubprofilebrowser.activities.databinding.ActivityRepoInformationBinding
import com.example.githubprofilebrowser.databinding.ActivityRepoInformationBinding

class RepoInformation : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRepoInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepoInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}