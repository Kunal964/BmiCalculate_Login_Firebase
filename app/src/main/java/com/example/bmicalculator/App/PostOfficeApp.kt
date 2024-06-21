package com.example.bmicalculator.App

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.Navigation.PostOfficeAppRouter
import com.example.bmicalculator.Navigation.Screen
import com.example.bmicalculator.Screens.HomeScreen
import com.example.bmicalculator.Screens.LoginScreen
import com.example.bmicalculator.Screens.SignUpScreen
import com.example.bmicalculator.Screens.TermsAndConditionScreen
import com.example.bmicalculator.data.home.HomeViewModel

@Composable
fun PostOfficeApp(homeViewModel: HomeViewModel = viewModel()) {
    homeViewModel.checkForActiveSession()
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (homeViewModel.isUserLoggedIn.value == true) {
                PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
            }
            Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentScreen->
                when (currentScreen.value) {
                    is Screen.SignUpScreen -> SignUpScreen()
                    is Screen.TermsAndConditionScreen -> TermsAndConditionScreen()
                    is Screen.LoginScreen -> LoginScreen()
                    is Screen.HomeScreen -> HomeScreen()
                }

            }

        }

    }
}