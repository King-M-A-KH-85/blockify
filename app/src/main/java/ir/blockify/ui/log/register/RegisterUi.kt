package ir.blockify.ui.log.register

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUi(activity: Activity, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // Create references for the composables to constrain
        val (fab, topImage, emailField, confirmPasswordField, passwordField, loginText, helpText) = createRefs()

        var emailText by remember { mutableStateOf(TextFieldValue("")) }
        var passwordText by remember { mutableStateOf(TextFieldValue("")) }
        var confirmPasswordText by remember { mutableStateOf(TextFieldValue("")) }

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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(emailField) {
                    top.linkTo(topImage.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
            value = emailText,
            onValueChange = {
                emailText = it
            },
            label = { Text("Email") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(passwordField) {
                    top.linkTo(emailField.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
            value = passwordText,
            onValueChange = {
                passwordText = it
            },
            label = { Text("Password") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(confirmPasswordField) {
                    top.linkTo(passwordField.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
            value = confirmPasswordText,
            onValueChange = {
                confirmPasswordText = it
            },
            label = { Text("confirm your password") }
        )

        Row(modifier = Modifier.constrainAs(loginText) {
            top.linkTo(confirmPasswordField.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
        }) {
            Text(text = "have account?")
            ClickableText(
                text = AnnotatedString("login"),
                style = TextStyle(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(modifier = Modifier.constrainAs(helpText) {
            top.linkTo(loginText.bottom, margin = 16.dp)
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
                    navController.context,
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
