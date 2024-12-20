package net.iessochoa.joseantoniolopez.t14_firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.login.LoginScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.Register.RegisterScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.starup.StarUpScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StarUpScreenDestination
    ){
        composable<StarUpScreenDestination> {
            StarUpScreen(
                navigateToLogin = { navController.navigate(LoginScreenDestination) },
                navigateToRegister = { navController.navigate(RegisterScreenDestination) }
            )
        }
        composable<LoginScreenDestination> {
            LoginScreen(

            )
        }
        composable<RegisterScreenDestination> {
            RegisterScreen()

        }

    }
}