package com.clasic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clasic.activity.ui.screens.*
import com.clasic.activity.ui.theme.ClasicActivityTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClasicActivityTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val drawerState = rememberDrawerState(androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Menú Principal",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Divider()

                val menuItems = listOf(
                    MenuItem("Perfil", Icons.Default.Person, "perfil"),
                    MenuItem("Fotos", Icons.Default.Photo, "fotos"),
                    MenuItem("Video", Icons.Default.PlayArrow, "video"),
                    MenuItem("Web", Icons.Default.Public, "web"),
                    MenuItem("Botones", Icons.Default.Settings, "botones")
                )

                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        selected = false,
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        onClick = {
                            navController.navigate(item.route)
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                NavigationDrawerItem(
                    label = { Text("Salir") },
                    selected = false,
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Salir") },
                    onClick = { /* Acción salir */ },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Mi Aplicación",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController, startDestination = "perfil") {
                    composable("perfil") { PerfilScreen() }
                    composable("fotos") { FotosScreen() }
                    composable("video") { VideoScreen() }
                    composable("web") { WebScreen() }
                    composable("botones") { BotonesScreen() }
                }
            }
        }
    }
}

data class MenuItem(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)