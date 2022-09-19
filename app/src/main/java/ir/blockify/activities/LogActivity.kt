package ir.blockify.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ir.blockify.ui.log.login.LoginUi
import ir.blockify.ui.log.register.RegisterUi
import ir.blockify.ui.theme.LogActivityTheme
import ir.blockify.utils.composable

class LogActivity : ComponentActivity() {
    private lateinit var openExitDialog: MutableState<Boolean>
    private lateinit var openLoginExceptionDialog: MutableState<Boolean>
    private lateinit var loginException: MutableState<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this) {
            // Back is pressed... Finishing the activity
            openExitDialog.value = true
        }

        setContent {
            LogActivityTheme {
                openExitDialog = remember { mutableStateOf(false) }
                openLoginExceptionDialog = remember { mutableStateOf(false) }
                loginException = remember { mutableStateOf("") }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "login") {
                        composable(route = "login") {
                            LoginUi(this@LogActivity, navController)
                        }
                        composable(route = "register") {
                            RegisterUi(this@LogActivity, navController)
                        }
                        composable(route = "profile") {
                        }
                        composable(route = "name") {
                        }
                    }

                    if (openExitDialog.value) {
                        AlertDialog(
                            modifier = Modifier.fillMaxWidth(),
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openExitDialog.value = false
                            },
                            title = {
                                Text(text = "Exit App")
                            },
                            text = {
                                Text("Exit of App?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        openExitDialog.value = false
                                        if (!navController.navigateUp())
                                            finish()
//                                        super.onBackPressed()
                                    }) {
                                    Text("Y")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        openExitDialog.value = false
                                    }) {
                                    Text("N")
                                }
                            }
                        )
                    }

                    if (openLoginExceptionDialog.value) {
                        AlertDialog(
                            modifier = Modifier.fillMaxWidth(),
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openExitDialog.value = false
                            },
                            title = {
                                Text(text = "Login Field")
                            },
                            text = {
                                loginException
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        openExitDialog.value = false
                                    }) {
                                    Text("OK")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
