package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.R
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthViewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.muestraEstado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.components.PasswordOutLinedTextField

@Composable
fun RegisterScreen(
    onLoginSuccess: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("pepe@correo.es") }
    var password by remember { mutableStateOf("123456") }
    var confirmPassword by remember { mutableStateOf("123456") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Crear nueva cuenta",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        /*OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )*/
        PasswordOutLinedTextField(
            label = "Contraseña",
            password,
            { password = it }
        )
        PasswordOutLinedTextField(
            label = "Repite Contraseña",
            confirmPassword,
            { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (password == confirmPassword) {
                viewModel.register(email, password)
            }else
                viewModel.contrasenyaNoConciden()

        }) {
            Text("Registrarse")
        }
        Spacer(modifier = Modifier.height(16.dp))

        muestraEstado(uiState, onLoginSuccess)
       /* when (uiState) {
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

        }*/
        Spacer(modifier = Modifier.weight(1f))
    }
}

