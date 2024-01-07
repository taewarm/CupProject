package com.example.cupproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupproject.view.MainView
import com.example.cupproject.viewmodel.MainViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private val viewModel by lazy {
        ViewModelProvider(
            this, MainViewModel.MainViewModelFactory(
                MainViewModel.MainRepositoryImpl(Firebase.database.reference)
            )
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val token = intent.extras?.getString("token")
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Main") {
                composable("Main") {
                    MainView(viewModel).MainView(token = token ?: "")
                }
//                composable("MainView/{token}") {
//                    val token = it.arguments?.getString("token") as String
//                    MainView().MainView(token)
//                }
            }
        }
    }
}