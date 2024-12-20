package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState

@Composable
fun MuestraEstado(uiState: AuthState, onLoginSuccess: () -> Unit){
    when (uiState) {
        is AuthState.Idle -> {}
        is AuthState.Loading -> CircularProgressIndicator()
        is AuthState.Success -> {
            Text("Login successful!")
            onLoginSuccess()
        }
        is AuthState.ErrorContrasenyaNoConciden -> {
            Text("Las contraseñas no coinciden",color = MaterialTheme.colorScheme.error)
        }
        is AuthState.Error -> Text(
            (uiState as AuthState.Error).exception,color = MaterialTheme.colorScheme.error
        )
    }
}