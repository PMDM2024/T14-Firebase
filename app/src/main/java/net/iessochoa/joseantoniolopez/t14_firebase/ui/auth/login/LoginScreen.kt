package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.login

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
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.Encabezado

import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.MuestraEstado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.PasswordOutLinedTextField
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.esCorrectoEmail

/**
 * Composable que representa la pantalla de inicio de sesión.
 * Gestiona el estado de la autenticación y proporciona una interfaz interactiva para ingresar email y contraseña.
 *
 * @param onBack Callback que se ejecuta cuando el usuario presiona el botón "Atrás".
 * @param onLoginSuccess Callback que se ejecuta cuando el inicio de sesión es exitoso.
 * @param viewModel Instancia de `AuthViewModel` para gestionar la lógica de autenticación.
 */
@Composable
fun LoginScreen(
    onBack: () -> Unit = {}, // Acción por defecto para el botón "Atrás"
    onLoginSuccess: () -> Unit = {}, // Acción por defecto para el éxito en el inicio de sesión
    viewModel: AuthViewModel = viewModel(), // ViewModel para la autenticación
    onResetPassword: () -> Unit={}
) {
    // Estado actual de la autenticación observado desde el ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Estados locales para el email y la contraseña
    var email by remember { mutableStateOf("pepe@correo.es") } // Email inicial de ejemplo
    var password by remember { mutableStateOf("123456") } // Contraseña inicial de ejemplo

    // Estructura de la interfaz
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño disponible
            .padding(16.dp), // Espaciado alrededor
        horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal centrada
        verticalArrangement = Arrangement.Center // Alineación vertical centrada
    ) {
        // Encabezado con botón "Atrás" y título
        Encabezado(
            onBack = onBack,
            titulo = "Iniciar Sesión",
            modifier = Modifier
                .weight(1f) // Ocupa un peso proporcional dentro del contenedor
                .fillMaxWidth() // Ocupa todo el ancho disponible
        )

        // Campo de texto para el email
        OutlinedTextField(
            value = email, // Texto actual del campo
            onValueChange = { email = it }, // Actualización del texto ingresado
            label = { Text("Email") }, // Etiqueta para el campo
            modifier = Modifier.fillMaxWidth() // Campo ocupa todo el ancho disponible
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos

        // Campo de texto personalizado para la contraseña
        PasswordOutLinedTextField(
            label = "Contraseña", // Etiqueta para el campo
            password = password, // Contraseña actual
            onValueChange = { password = it } // Actualización de la contraseña
        )
        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Botón para iniciar sesión
        Button(onClick = {
            //si no son correctos los campos muestra un error
            if(email.isEmpty()||password.isEmpty())
                viewModel.camposVacios()
            else if(!esCorrectoEmail(email))
                viewModel.emailIncorrecto()
            else //  inicio de sesión
                viewModel.login(email, password)
        }) {
            Text("Login") // Texto dentro del botón
        }
        // Vamos a recuperar la contraseña
        Button(onClick = {
           onResetPassword()
        }) {
            Text("Restablecer contraseña") // Texto dentro del botón
        }
        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Manejo del estado de éxito en la autenticación
        if (uiState is AuthState.Success) {
            // Evita múltiples llamados a `onLoginSuccess` durante recomposiciones
            if (!viewModel.iniciadaSesion) {
                onLoginSuccess() // Navega a la siguiente pantalla
                viewModel.iniciadaSesion = true // Marca la sesión como iniciada
            }
        } else {
            // Muestra el estado actual de la autenticación (cargando, error, etc.)
            MuestraEstado(uiState)
        }
        Spacer(modifier = Modifier.height(100.dp)) // Espacio final
    }
}