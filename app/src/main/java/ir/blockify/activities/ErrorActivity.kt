package ir.blockify.activities

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.blockify.R
import ir.blockify.crash.activity.CrashActivity
import ir.blockify.crash.config.CrashConfig
import ir.blockify.ui.theme.BlockifyTheme
import ir.blockify.ui.theme.ErrorActivityTheme

class ErrorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        config = CrashActivity.getConfigFromIntent(intent)
        activity = this@ErrorActivity


        setContent {
            ErrorActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    MainScreen()
                }
            }
        }
    }

    lateinit var config: CrashConfig
    lateinit var activity: Activity

    @Preview
    @Composable
    fun MainScreen() {

        val navController = rememberNavController()
        NavHost(navController, startDestination = "main") {
            composable(route = "main") {
                MainDetail(navController)
            }
            composable(route = "detail") {
                ErrorDetail(navController)
            }
        }
    }

    @Composable
    fun MainDetail(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_bug),
                contentDescription = "Bug icon",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
            Text(
                text = "An unexpected error occurred .\nSorry for the inconvenience.",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 0.dp, top = 30.dp, bottom = 0.dp)
            )
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 70.dp,
                    bottom = 0.dp
                )
            ) {
                Button(
                    onClick = {
                        if (config.isShowRestartButton && config.restartActivityClass != null) {
                            CrashActivity.restartApplication(
                                activity,
                                config
                            )
                        } else {
                            CrashActivity.closeApplication(
                                activity,
                                config
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(1f)
                ) {
                    Text(text = "RESTART")
                }
                Button(
                    onClick = {
                        navController.navigate("detail")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(text = "DETAIL")
                }
            }
        }
    }

    @Composable
    fun ErrorDetail(navController: NavController) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (backIcon, title) = createRefs()

                IconButton(onClick = {
                    navController.navigateUp()
                }, modifier = Modifier.constrainAs(backIcon) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "back",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
                    )
                }

                Text(
                    text = "Crash detail",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
            SelectionContainer {
                Text(
                    text = CrashActivity.getAllErrorDetailsFromIntent(
                        activity,
                        intent
                    ), textAlign = TextAlign.Left, modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}
