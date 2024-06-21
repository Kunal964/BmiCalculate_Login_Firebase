package com.example.bmicalculator.data.login

data class LoginUIState(
    var email :String = "",
     var password :String = "",

    var emailError :Boolean = false,
    var passwordError :Boolean = false,
)