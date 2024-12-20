package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.login

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
import net.iessochoa.joseantoniolopez.t14_firebase.R
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.Encabezado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.Logo

import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.MuestraEstado
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components.PasswordOutLinedTextField

@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("pepe@correo.es") }
    var password by remember { mutableStateOf("123456") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Encabezado(
            onBack = onBack,
            titulo = "Iniciar Sesión",
            modifier = Modifier.weight(1f)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        PasswordOutLinedTextField(
            label = "Contraseña",
            password,
            { password = it },

        )
        Spacer(modifier = Modifier.height(16.dp))
        //login
        Button(onClick = {
            viewModel.login(email, password)
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))
        MuestraEstado(uiState, onLoginSuccess)
        Spacer(modifier = Modifier.height(100.dp))
    }
}

