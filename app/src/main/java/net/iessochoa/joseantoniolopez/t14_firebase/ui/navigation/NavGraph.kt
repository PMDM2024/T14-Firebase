package net.iessochoa.joseantoniolopez.t14_firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.login.LoginScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.register.RegisterScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.resetpassword.ResetPasswordScreen

import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.starup.StarUpScreen
import net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen.PrincipalScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    //lambda que nos permite navegar a la pantalla Principal de la App una vez se haya logueado
    val onLogin_RegistroExitoso = {
        navController.navigate(
            PrincipalScreenDestination
        ) {//Una vez se haya logueado se eliminan de la pila las pantallas de inicio de sesion
            popUpTo(LoginScreenDestination) { inclusive = true }
            popUpTo(StarUpScreenDestination) { inclusive = true }

        }
    }
    NavHost(
        navController = navController,
        startDestination = StarUpScreenDestination
    ) {
        //Pantalla de inicio de la App
        composable<StarUpScreenDestination> {
            StarUpScreen(
                navigateToLogin = { navController.navigate(LoginScreenDestination) },
                navigateToRegister = { navController.navigate(RegisterScreenDestination) },
                navigateToPrincipal = {
                    navController.navigate(PrincipalScreenDestination) {
                        popUpTo(StarUpScreenDestination) { inclusive = true }
                    }
                }
            )
        }
        //Pantallas de login
        composable<LoginScreenDestination> {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onLoginSuccess = onLogin_RegistroExitoso,
                onResetPassword = {
                    navController.navigate(ResetPasswordScreenDestination) {
                        popUpTo(LoginScreenDestination) { inclusive = true }
                    }
                }
            )
        }
        //Pantalla de Registro de usuario
        composable<RegisterScreenDestination> {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onRegisterSuccess = onLogin_RegistroExitoso
            )
        }
        //Pantalla de restablecimiento de contraseña
        composable<ResetPasswordScreenDestination> {
            ResetPasswordScreen(
                onBack = { navController.popBackStack() }
            )
        }
        //Pantalla principal de la App una vez logueado
        composable<PrincipalScreenDestination> {
            PrincipalScreen(
                //si cierra sesion se vuelve a la pantalla de inicio
                onLogout = {
                    navController.navigate(
                        StarUpScreenDestination
                    ) {
                        //eliminamos de la pila la pantalla actual de la pila
                        popUpTo(PrincipalScreenDestination) { inclusive = true }
                    }

                }
            )
        }

    }
}
