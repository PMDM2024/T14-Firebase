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
import com.google.android.play.integrity.internal.u
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthViewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.Encabezado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.MuestraEstado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.PasswordOutLinedTextField
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.esCorrectoEmail

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
            onValueChange = { displayName = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo de texto para ingresar el email.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos.

        // Campo de texto personalizado para ingresar la contraseña.
        PasswordOutLinedTextField(
            label = "Contraseña",
            password = password,
            onValueChange = { password = it }
        )

        // Campo de texto personalizado para confirmar la contraseña.
        PasswordOutLinedTextField(
            label = "Repite Contraseña",
            password = confirmPassword,
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre elementos.

        // Botón para realizar el registro.
        Button(onClick = {
            //si no son correctos los campos muestra un error
            if(email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()||displayName.isEmpty())
                viewModel.camposVacios()
            else if(!esCorrectoEmail(email))
                viewModel.emailIncorrecto()
            else if (password != confirmPassword) {
                // Si las contraseñas coinciden, llama al método de registro del ViewModel.
                viewModel.contrasenyaNoConciden()
            } else if (password.length < 6) {
                viewModel.contrasenyaMenor()

            }
            else {//registramos al usuario
                viewModel.register(email, password,displayName)
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
