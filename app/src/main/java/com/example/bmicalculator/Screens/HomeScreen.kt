package com.example.bmicalculator.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.R
import com.example.bmicalculator.components.*
import com.example.bmicalculator.data.home.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val feetValue = remember { mutableStateOf("") }
    val inchesValue = remember { mutableStateOf("") }
    val weightValue = remember { mutableStateOf("") }
    val bmiValue = remember { mutableDoubleStateOf(0.0) }
    val bmiStatus = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        homeViewModel.getUserData()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerHeader(homeViewModel.emailId.value)
                NavigationDrawerBody(
                    navigationDrawerItems = homeViewModel.navigationItemsList,
                    onNavigationItemClicked = {
                        Log.d("ComingHere", "inside_NavigationItemClicked")
                        Log.d("ComingHere", "${it.itemId} ${it.title}")
                    }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                AppToolbar(
                    toolbarTitle = stringResource(id = R.string.home),
                    logoutButtonClicked = {
                        homeViewModel.logout()
                    },
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        HeightTextFieldComponent(feetValue = feetValue, inchesValue = inchesValue)
                        Spacer(modifier = Modifier.height(15.dp))
                        WeightTextFieldComponent(weightValue = weightValue)
                        Spacer(modifier = Modifier.height(20.dp))
                        MyButtonComponent(value = "Calculate BMI", onButtonClicked = {
                            bmiValue.doubleValue = calculateBMI(feetValue.value, inchesValue.value, weightValue.value)
                            bmiStatus.value = interpretBMI(bmiValue.doubleValue)
                        },
                            isEnabled = true)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "BMI: ${bmiValue.doubleValue}",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = bmiStatus.value,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

fun calculateBMI(feet: String, inches: String, weight: String): Double {
    val feetValue = feet.toDoubleOrNull() ?: return 0.0
    val inchesValue = inches.toDoubleOrNull() ?: return 0.0
    val weightValue = weight.toDoubleOrNull() ?: return 0.0

    val heightInInches = (feetValue * 12) + inchesValue
    val heightInMeters = heightInInches * 0.0254

    return if (heightInMeters > 0) {
        weightValue / (heightInMeters * heightInMeters)
    } else {
        0.0
    }
}

fun interpretBMI(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 24.9 -> "Normal weight"
        bmi < 29.9 -> "Overweight"
        else -> "Obesity"
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
