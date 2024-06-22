package com.example.bmicalculator.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.data.home.HomeViewModel
import com.example.bmicalculator.R
import com.example.bmicalculator.data.NavigationItem

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) } ,
        colors = OutlinedTextFieldDefaults.colors (
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
            cursorColor = colorResource(id = R.color.colorPrimary),
           unfocusedContainerColor = colorResource(id = R.color.bgColor)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus

    )
}
@Composable
fun PasswordTextFieldComponent (
    labelValue: String,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
    ) {
    val localFocusManager = LocalFocusManager.current

    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) } ,
        colors = OutlinedTextFieldDefaults.colors (
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
            cursorColor = colorResource(id = R.color.colorPrimary),
            unfocusedContainerColor = colorResource(id = R.color.bgColor)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            }
            else {
                Icons.Filled.VisibilityOff
            }
            val description = if(passwordVisible.value) {
                stringResource(id = R.string.hidePassword)
            }
            else {
                stringResource(id = R.string.showPassword)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)

            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()

    )
}

@Composable
fun CheckBoxComponent(
    value: String,
    onTextSelected : (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val checkedState = remember { mutableStateOf(false) }
        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value = !checkedState.value
            onCheckedChange.invoke(it)
        })
        ClickableTextComponent(value = value, onTextSelected)

    }


}

@Composable
fun ClickableTextComponent(value: String, onTextSelected : (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = " and "
    val termsAndConditionsText = " Terms of Use"
    
    val annotatedString = buildAnnotatedString { 
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
            
        }
        append(andText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
        
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d( "ClickableTextComponent", "{${span.item}}")
                if (span.item == termsAndConditionsText || span.item == privacyPolicyText) {
                    onTextSelected(span.item)
                }
            }

        
    }) 
}

@Composable
fun MyButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked()
        },
           contentPadding = PaddingValues(),
         colors = ButtonDefaults.buttonColors(Color.Transparent),
         enabled = isEnabled
    )
    {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.colorPrimary),
                        colorResource(id = R.color.colorSecondary)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp)
        
        Text(
            modifier = Modifier.
            padding(8.dp),
            text = "or", fontSize = 15.sp, color = colorResource(id = R.color.colorText))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp)
        
    }

}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected : (String) -> Unit) {
    val initialText = if(tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if(tryingToLogin) "Log In" else "Register "

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = loginText , annotation = loginText )
            append(loginText )

        }

    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d( "ClickableTextComponent", "{${span.item}}")
                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }


    })
}

@Composable
fun UnderlinedNormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    toolbarTitle: String,
    logoutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary)
        ),
        title = {
            Text(
                text = toolbarTitle,
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu),
                    tint = colorResource(id = R.color.white)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                logoutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = stringResource(id = R.string.logout),
                    tint = colorResource(id = R.color.white)
                )
            }
        }
    )
}

@Composable
fun NavigationDrawerHeader(value: String?) {
    Box(
        modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.colorPrimary),
                        colorResource(id = R.color.colorSecondary)
                    )
                )
            )
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {
        NavigationDrawerText(
            title = value ?: stringResource(R.string.navigation_header),
            textUnit = 28.sp,
            color = colorResource(id = R.color.colorAccent)
        )
    }
}

// NavigationDrawerBody.kt
@Composable
fun NavigationDrawerBody(navigationDrawerItems: List<NavigationItem>, onNavigationItemClicked: (NavigationItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(navigationDrawerItems) {
            NavigationItemRow(item = it, onNavigationItemClicked)
        }
    }
}
@Composable
fun NavigationItemRow(item: NavigationItem, onNavigationItemClicked:(NavigationItem) -> Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigationItemClicked.invoke(item)
            }
            .padding(all = 16.dp)
    ) {

        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
        )

        Spacer(modifier = Modifier.width(18.dp))

        NavigationDrawerText(title = item.title, 18.sp, colorResource(id = R.color.colorPrimary))


    }
}

@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit, color: Color) {

    val shadowOffset = Offset(4f, 6f)

    Text(
        text = title, style = TextStyle(
            color = Color.Black,
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
            shadow = Shadow(
                color = colorResource(id = R.color.colorPrimary),
                offset = shadowOffset, 2f
            )
        )
    )
}

@Composable
fun AgeTextFieldComponent(labelValue: String) {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) } ,
        colors = OutlinedTextFieldDefaults.colors (
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
            cursorColor = colorResource(id = R.color.colorPrimary),
            unfocusedContainerColor = colorResource(id = R.color.bgColor)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },

    )
}

@Composable
fun HeightTextFieldComponent(feetValue: MutableState<String>, inchesValue: MutableState<String>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .clip(componentShapes.small)
                .padding(end = 8.dp),
            label = { Text(text = "Feet") },
            colors = OutlinedTextFieldDefaults.colors (
                focusedBorderColor = colorResource(id = R.color.colorPrimary),
                unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
                cursorColor = colorResource(id = R.color.colorPrimary),
                unfocusedContainerColor = colorResource(id = R.color.bgColor)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            value = feetValue.value,
            onValueChange = {
                feetValue.value = it
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .clip(componentShapes.small),
            label = { Text(text = "Inches") },
            colors = OutlinedTextFieldDefaults.colors (
                focusedBorderColor = colorResource(id = R.color.colorPrimary),
                unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
                cursorColor = colorResource(id = R.color.colorPrimary),
                unfocusedContainerColor = colorResource(id = R.color.bgColor)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            value = inchesValue.value,
            onValueChange = {
                inchesValue.value = it
            }
        )
    }
}

@Composable
fun WeightTextFieldComponent(weightValue: MutableState<String>) {
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(componentShapes.small),
        label = { Text(text = "Weight (kg)") },
        colors = OutlinedTextFieldDefaults.colors (
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.colorSecondary),
            cursorColor = colorResource(id = R.color.colorPrimary),
            unfocusedContainerColor = colorResource(id = R.color.bgColor)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        singleLine = true,
        maxLines = 1,
        value = weightValue.value,
        onValueChange = {
            weightValue.value = it
        }
    )
}

