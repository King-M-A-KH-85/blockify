package ir.blockify.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ir.blockify.ui.theme.MainActivityTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showMenu by remember { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text("Blockify") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                }, actions = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Default.Favorite, contentDescription = "")
                                    }
                                    IconButton(onClick = { showMenu = !showMenu }) {
                                        Icon(Icons.Default.MoreVert, contentDescription = "")
                                    }
                                    DropdownMenu(
                                        expanded = showMenu,
                                        onDismissRequest = { showMenu = false }
                                    ) {
                                        DropdownMenuItem(text = {
                                            Icon(
                                                Icons.Filled.Refresh,
                                                contentDescription = ""
                                            )
                                        }, onClick = { /*TODO*/ })
                                        DropdownMenuItem(text = {
                                            Icon(
                                                Icons.Filled.Call,
                                                contentDescription = ""
                                            )
                                        }, onClick = { /*TODO*/ })
                                    }
                                }

                            )
                        }, content = {
                            Column(
                                modifier = Modifier
                                    .padding(it)
                                    .fillMaxSize()
                            ) {
                                BottomAppBar() {
                                    NavigationBarItem(
                                        selected = true,
                                        onClick = { /*TODO*/ },
                                        icon = {
                                            Icon(
                                                Icons.Filled.Call,
                                                contentDescription = ""
                                            )
                                        })
                                    NavigationBarItem(
                                        selected = false,
                                        onClick = { /*TODO*/ },
                                        icon = {
                                            Icon(
                                                Icons.Filled.Refresh,
                                                contentDescription = ""
                                            )
                                        })
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
