package ir.blockify.ui.log.login

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import ir.blockify.R
import ir.blockify.activities.MainActivity
import ir.blockify.utils.validateEmail
import ir.blockify.utils.validatePassword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUi(activity: Activity, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (fab, topImage, emailField, passwordField, registerText, helpText) = createRefs()

        var emailText by remember { mutableStateOf(TextFieldValue("")) }
        var passwordText by remember { mutableStateOf(TextFieldValue("")) }

        var emailFieldError by remember { mutableStateOf(false) }
        var passwordFieldError by remember { mutableStateOf(false) }

        Image(
            painter = painterResource(id = R.drawable.a),
            contentDescription = "test",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .constrainAs(topImage) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(emailField) {
                top.linkTo(topImage.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailText,
                onValueChange = {
                    emailText = it
                    emailFieldError = validateEmail(emailText.text)
                },
                trailingIcon = {
                    if (emailFieldError)
                        Icon(
                            Icons.Filled.Warning,
                            "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                singleLine = true,
                isError = emailFieldError,
                label = { Text("Email") }
            )
            if (emailFieldError) {
                Text(
                    text = "email error",
                    color = MaterialTheme.colorScheme.error,
//                        style = MaterialTheme.typography.caption,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        // password field
        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(passwordField) {
                top.linkTo(emailField.bottom, margin = 8.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    passwordFieldError = validatePassword(passwordText.text)
                },
                trailingIcon = {
                    if (passwordFieldError)
                        Icon(
                            Icons.Filled.Warning,
                            "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                singleLine = true,
                isError = passwordFieldError,
                label = { Text("Password") }
            )
            if (passwordFieldError) {
                Text(
                    text = "password error",
                    color = MaterialTheme.colorScheme.error,
//                        style = MaterialTheme.typography.caption,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Row(modifier = Modifier.constrainAs(registerText) {
            top.linkTo(passwordField.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
        }) {
            Text(text = "haven't account?")
            ClickableText(
                text = AnnotatedString("register"),
                style = TextStyle(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    navController.navigate("register")
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(modifier = Modifier.constrainAs(helpText) {
            top.linkTo(registerText.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
        }) {
            Text(text = "need help?")
            ClickableText(
                text = AnnotatedString("help"),
                style = TextStyle(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    navController.navigate("help")
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        FloatingActionButton(onClick = {
            activity.startActivity(
                Intent(
                    activity,
                    MainActivity::class.java
                )
            )
            activity.finish()
        }, modifier = Modifier.constrainAs(fab) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                contentDescription = "next"
            )
        }
    }


}
