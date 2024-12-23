package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthViewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.Encabezado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.MuestraEstado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.PasswordOutLinedTextField

/**
 * Composable que representa la pantalla de registro de usuario.
 * Permite al usuario crear una nueva cuenta ingresando email, contraseña y confirmación de contraseña.
 * Gestiona el estado de la autenticación y proporciona retroalimentación visual sobre el estado actual.
 *
 * @param onBack Callback que se ejecuta cuando el usuario presiona el botón "Atrás".
 * @param onRegisterSuccess Callback que se ejecuta cuando el registro es exitoso.
 * @param viewModel Instancia de `AuthViewModel` para gestionar la lógica de autenticación.
 */
@Composable
fun RegisterScreen(
    onBack: () -> Unit = {}, // Acción predeterminada para el botón "Atrás".
    onRegisterSuccess: () -> Unit = {}, // Acción predeterminada al completar el registro.
    viewModel: AuthViewModel = viewModel() // ViewModel para gestionar la lógica de autenticación.
) {
    // Observa el estado actual de la autenticación desde el ViewModel.
    val uiState by viewModel.uiState.collectAsState()

    // Estados locales para el email, la contraseña y su confirmación.
    var displayName by remember { mutableStateOf("Pepe Ramírez") }
    var email by remember { mutableStateOf("pepe@correo.es") } // Valor inicial de ejemplo para el email.
    var password by remember { mutableStateOf("123456") } // Valor inicial de ejemplo para la contraseña.
    var confirmPassword by remember { mutableStateOf("123456") } // Contraseña de confirmación.

    // Estructura de la interfaz de usuario.
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño disponible.
            .padding(16.dp), // Espaciado alrededor.
        horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal centrada.
        verticalArrangement = Arrangement.Center // Alineación vertical centrada.
    ) {
        // Encabezado con el botón "Atrás" y título.
        Encabezado(
            onBack = onBack,
            titulo = "Crear nueva cuenta",
            modifier = Modifier
                .weight(1f) // Asigna un peso proporcional dentro del contenedor.
                .fillMaxWidth() // Ocupa todo el ancho disponible.
        )
        OutlinedTextField(
            value = displayName, // Valor actual del email.
            onValueChange = { displayName = it }, // Callback para actualizar el email.
            label = { Text("Nombre") }, // Etiqueta del campo.
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible.
        )
        // Campo de texto para ingresar el email.
        OutlinedTextField(
            value = email, // Valor actual del email.
            onValueChange = { email = it }, // Callback para actualizar el email.
            label = { Text("Email") }, // Etiqueta del campo.
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible.
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos.

        // Campo de texto personalizado para ingresar la contraseña.
        PasswordOutLinedTextField(
            label = "Contraseña", // Etiqueta del campo.
            password = password, // Valor actual de la contraseña.
            onValueChange = { password = it } // Callback para actualizar la contraseña.
        )

        // Campo de texto personalizado para confirmar la contraseña.
        PasswordOutLinedTextField(
            label = "Repite Contraseña", // Etiqueta del campo.
            password = confirmPassword, // Valor actual de la confirmación de contraseña.
            onValueChange = { confirmPassword = it } // Callback para actualizar la confirmación.
        )
        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre elementos.

        // Botón para realizar el registro.
        Button(onClick = {
            if (password == confirmPassword) {
                // Si las contraseñas coinciden, llama al método de registro del ViewModel.
                viewModel.register(email, password)
            } else {
                // Si no coinciden, actualiza el estado para mostrar el error correspondiente.
                viewModel.contrasenyaNoConciden()
            }
        }) {
            Text("Registrarse") // Texto del botón.
        }
        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre elementos.

        // Manejo del estado de éxito en el registro.
        if (uiState is AuthState.Success) {
            // Evita múltiples llamados a `onRegisterSuccess` durante recomposiciones.
            if (!viewModel.iniciadaSesion) {
                onRegisterSuccess() // Navega a la siguiente pantalla.
                viewModel.iniciadaSesion = true // Marca la sesión como iniciada.
            }
        } else {
            // Muestra el estado actual de la autenticación (cargando, error, etc.).
            MuestraEstado(uiState)
        }

        Spacer(modifier = Modifier.height(100.dp)) // Espaciado final.
    }
}
