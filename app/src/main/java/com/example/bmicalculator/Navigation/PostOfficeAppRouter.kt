package com.example.bmicalculator.Navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen() {
    object SignUpScreen : Screen()
    object TermsAndConditionScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
}
object PostOfficeAppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

}