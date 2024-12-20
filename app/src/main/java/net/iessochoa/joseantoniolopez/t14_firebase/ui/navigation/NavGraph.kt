package net.iessochoa.joseantoniolopez.t14_firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.login.LoginScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.register.RegisterScreen

import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.starup.StarUpScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen.PrincipalScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StarUpScreenDestination
    ) {
        composable<StarUpScreenDestination> {
            StarUpScreen(
                navigateToLogin = { navController.navigate(LoginScreenDestination) },
                navigateToRegister = { navController.navigate(RegisterScreenDestination) }
            )
        }
        composable<LoginScreenDestination> {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onLoginSuccess = {
                    navController.navigate(
                        PrincipalScreenDestination
                    ) /*{
                        popUpTo(LoginScreenDestination) { inclusive = true }
                        popUpTo(StarUpScreenDestination) { inclusive = true }

                    }*/
                }
            )
            composable<RegisterScreenDestination> {
                RegisterScreen(
                    onBack = { navController.popBackStack() },
                )
            }
            composable<PrincipalScreenDestination> {
                PrincipalScreen(
                    onLogout = {
                        navController.navigate(
                            StarUpScreenDestination
                        ) {
                            //eliminamos de la pila la pantalla actual
                            popUpTo(PrincipalScreenDestination) { inclusive = true }
                        }

                    }
                )
            }

        }
    }
}