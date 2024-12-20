package net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.components.AppBar


@Composable
fun PrincipalScreen(
    onLogout: () -> Unit = {},
    viewModel: PrincipalViewModel = viewModel()
)
{
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = "Principal",
                puedeNavegarAtras = false,
            )
        }
    ){padding->
        Column(modifier = Modifier.padding(padding)) {
            Text(text = "Email: ${uiState.email}")
            Button(onClick = {
                viewModel.logout()
                onLogout()
            }
            ) {
                Text(text = "Logout")
            }
        }

    }
}