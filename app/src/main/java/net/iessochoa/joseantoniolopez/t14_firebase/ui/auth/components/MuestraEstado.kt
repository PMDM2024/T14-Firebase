package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState

/**
 * Composable que muestra el estado actual de la autenticación.
 * Dependiendo del estado proporcionado, renderiza diferentes elementos de UI.
 *
 * @param uiState El estado de la autenticación, representado como una instancia de la clase sellada `AuthState`.
 */
@Composable
fun MuestraEstado(uiState: AuthState) {
    // Estructura condicional para renderizar diferentes contenidos según el estado de autenticación
    when (uiState) {
        // Estado inicial donde no se realiza ninguna acción
        is AuthState.Idle -> {
            // No se muestra ningún contenido en este estado
        }

        // Estado de carga que muestra un indicador de progreso circular
        is AuthState.Loading -> CircularProgressIndicator()

        // Estado de éxito, muestra un mensaje indicando que el login fue exitoso
        is AuthState.Success -> {
            Text("Login successful!") // Muestra un mensaje de éxito
            // Lógica adicional comentada: podría realizar una acción como iniciar sesión
            // if(!iniciadaSesion) onLoginSuccess()
        }

        // Error específico: las contraseñas no coinciden
        is AuthState.ErrorContrasenyaNoConciden -> {
            Text(
                text = "Las contraseñas no coinciden", // Muestra el error
                color = MaterialTheme.colorScheme.error // Aplica color de error del tema actual
            )
        }

        // Estado de error genérico, muestra el mensaje de error proporcionado
        is AuthState.Error -> {
            Text(
                text = (uiState as AuthState.Error).exception, // Mensaje de error
                color = MaterialTheme.colorScheme.error // Aplica color de error del tema actual
            )
        }
    }
}