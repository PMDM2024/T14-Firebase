package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import net.iessochoa.joseantoniolopez.t14_firebase.data.repository.Repository
import java.lang.Exception
import java.net.ConnectException

/**
 * ViewModel encargado de gestionar la lógica de autenticación.
 * Proporciona un flujo de estados de UI (`uiState`) para representar los diferentes estados de la autenticación.
 */
class AuthViewModel : ViewModel() {

    // Al inicio el no hay estado
    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)

    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    // Indicador para evitar múltiples inicios de sesión al recomponer pantallas
    var iniciadaSesion = false

    /**
     * Inicia el proceso de inicio de sesión con el email y contraseña proporcionados.
     * Actualiza el estado de la UI según el resultado de la operación.
     */
    fun login(email: String, password: String) {
        _uiState.value = AuthState.Loading // Cambia el estado a "Cargando"

        viewModelScope.launch {
            val result = Repository.login(email, password) // Realiza la solicitud de login
            _uiState.value = if (result.isSuccess) {
                AuthState.Success // Login exitoso
            } else {
                estadoError(result) // Manejo de errores específicos
            }
        }
    }

    /**
     * Registra un nuevo usuario con el email y contraseña proporcionados.
     * Actualiza el estado de la UI según el resultado de la operación.
     */
    fun register(email: String, password: String, displayName: String="") {
        _uiState.value = AuthState.Loading // Cambia el estado a "Cargando"

        viewModelScope.launch {
            val result = Repository.register(email, password,displayName) // Realiza la solicitud de registro

            _uiState.value =if (result.isSuccess) {
                AuthState.Success // Registro exitoso
            } else {
                   estadoError(result) // Manejo de errores específicos
            }
        }
    }

    /**
     * Maneja los diferentes tipos de errores que pueden ocurrir durante las operaciones de autenticación.
     * Regresa el estado de error correspondiente basado en la excepción.
     */
    private fun estadoError(result: Result<Unit>) = when (result.exceptionOrNull() as? Exception) {
        is FirebaseAuthInvalidUserException -> {
            Log.e("AuthViewModel", "Error: usuario no encontrado")
            AuthState.Error("Usuario no encontrado")
        }
        is FirebaseAuthInvalidCredentialsException -> {
            Log.e("AuthViewModel", "Error de contraseña no válida")
            AuthState.Error("Contraseña o usuario no válido")
        }
        is ConnectException -> {
            Log.e("AuthViewModel", "Sin conexión a internet")
            AuthState.Error("Sin conexión a internet")
        }
        is FirebaseAuthUserCollisionException -> {
            Log.e("AuthViewModel", "Error: usuario ya registrado")
            AuthState.Error("Usuario ya existe")
        }
        else -> {
            Log.e("AuthViewModel", "Error desconocido: ${result.exceptionOrNull()}")
            AuthState.Error("Error desconocido")
        }
    }

    /**
     * Actualiza el estado de la UI indicando que las contraseñas no coinciden.
     */
    fun contrasenyaNoConciden() {
        _uiState.value = AuthState.ErrorContrasenyaNoConciden
    }

    /**
     * Realiza el cierre de sesión del usuario actual.
     */
    fun logout() {
        Repository.logout()
    }

    fun registraDisplayName() {
        //Repository.logout()
        /*val user = Repository.getCurrentUser()
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName("Jose Antonio")
            .build()
        viewModelScope.launch {
            val result = Repository.login(email, password) // Realiza la solicitud de login
            _uiState.value = if (result.isSuccess) {
                AuthState.Success // Login exitoso
            } else {
                estadoError(result) // Manejo de errores específicos
            }
        }
*/
    }
}

/**
 * Clase sellada que representa los diferentes estados de la autenticación.
 * Es parecido a un Enum pero con más opciones.
 */
sealed class AuthState {
    object Idle : AuthState() // Estado inicial sin actividad
    object Loading : AuthState() // Estado de carga
    object Success : AuthState() // Estado de éxito
    object ErrorContrasenyaNoConciden : AuthState() // Error: contraseñas no coinciden

    // Estado de error genérico con un mensaje asociado
    data class Error(val exception: String) : AuthState()
}