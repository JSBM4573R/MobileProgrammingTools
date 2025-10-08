package com.clasic.activity.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clasic.activity.ui.screens.BotonesScreen
import com.clasic.activity.ui.screens.FotosScreen
import com.clasic.activity.ui.screens.PerfilScreen
import com.clasic.activity.ui.screens.VideoScreen
import com.clasic.activity.ui.screens.WebScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Perfil.route
    ) {
        composable(route = Screens.Perfil.route) {
            PerfilScreen()
        }
        composable(route = Screens.Fotos.route) {
            FotosScreen()
        }
        composable(route = Screens.Video.route) {
            VideoScreen()
        }
        composable(route = Screens.Web.route) {
            WebScreen()
        }
        composable(route = Screens.Botones.route) {
            BotonesScreen()
        }
    }
}

sealed class Screens(val route: String) {
    object Perfil : Screens("perfil")
    object Fotos : Screens("fotos")
    object Video : Screens("video")
    object Web : Screens("web")
    object Botones : Screens("botones")
}