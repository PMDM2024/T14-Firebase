package net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.components.ActionItem
import net.iessochoa.joseantoniolopez.t14_firebase.ui.components.AppBar

/**
 * representa la pantalla principal de la aplicación.
 * La modificáis según vuestras necesidades
 */
@Composable
fun PrincipalScreen(
    onLogout: () -> Unit = {},//accion de logout
    viewModel: PrincipalViewModel = viewModel()
)
{
    //añadimos una acción a la barra de navegación para salir de la app
   val accionesBarra=listOf(
       //Acción de cerrar sesión
        ActionItem(
            name = "Cerra Sesión",
            icon = Icons.Filled.ExitToApp,
            action = {
                viewModel.cerrarSesion()
                onLogout()
            }
        )
   )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = "Principal",
                puedeNavegarAtras = false,
                listaAcciones = accionesBarra
            )
        }
    ){padding->
        Box(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Nombre : ${uiState.usuario.nombre} ")
                Text(text = "Email: ${uiState.usuario.email}")
            }
        }

    }
}