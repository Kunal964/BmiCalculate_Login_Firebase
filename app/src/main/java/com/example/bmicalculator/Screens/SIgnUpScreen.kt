package com.example.bmicalculator.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.Navigation.PostOfficeAppRouter
import com.example.bmicalculator.Navigation.Screen
import com.example.bmicalculator.R
import com.example.bmicalculator.components.*
import com.example.bmicalculator.data.signup.SignupUIEvent
import com.example.bmicalculator.data.signup.SignupViewModel

@Composable
fun SignUpScreen(viewModel: SignupViewModel = viewModel()) {
    val registrationUIState = viewModel.registrationUIState.value
    val allValidationsPassed = viewModel.allValidationsPassed.value
    val signUpInProgress = viewModel.signUpInProgress.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.firstname),
                painterResource = painterResource(id = R.drawable.profile_24),
                onTextChanged = {
                    viewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = viewModel.registrationUIState.value.firstNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.lastname),
                painterResource = painterResource(id = R.drawable.profile_24),
                onTextChanged = {
                    viewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                },
                errorStatus = viewModel.registrationUIState.value.lastNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextChanged = {
                    viewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                errorStatus = viewModel.registrationUIState.value.emailError
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                onTextChanged = {
                    viewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = viewModel.registrationUIState.value.passwordError
            )
            CheckBoxComponent(
                value = stringResource(id = R.string.terms),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionScreen)
                },
                onCheckedChange = {
                    viewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            MyButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    viewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                },
                isEnabled = allValidationsPassed
            )
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            })
        }
        if (signUpInProgress) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignupScreen() {
    SignUpScreen(viewModel = SignupViewModel())
}
