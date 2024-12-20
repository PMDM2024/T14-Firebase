package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.iessochoa.joseantoniolopez.t14_firebase.data.repository.Repository
import java.lang.Exception
import java.net.ConnectException

class AuthViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)
    val uiState: StateFlow<AuthState> get() = _uiState

    fun login(email: String, password: String) {
        _uiState.value = AuthState.Loading

        viewModelScope.launch {
            val result = Repository.login(email, password)
            _uiState.value = if (result.isSuccess) {
                AuthState.Success
            } else {
               // AuthState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
              //  AuthState.Error(result.exceptionOrNull() as? Exception?)
                estadoError(result)
            }

        }
    }



    fun register(email: String, password: String) {
        _uiState.value = AuthState.Loading

        viewModelScope.launch {
            val result = Repository.register(email, password)
            _uiState.value = if (result.isSuccess) {
                AuthState.Success
            } else {
                //AuthState.Error(result.exceptionOrNull() as? Exception?)
                estadoError(result)
            }
        }
    }
    private fun estadoError(result: Result<Unit>) =
        when (result.exceptionOrNull() as? Exception) {
            is FirebaseAuthInvalidUserException -> {
                Log.e("AuthViewModel", "Error: usuario no encontrado")
                AuthState.Error("Usuario no encontrado")
            }

            is FirebaseAuthInvalidCredentialsException -> {
                Log.e("AuthViewModel", "Error de contraseña no válida")
                AuthState.Error("Contraseña no válida")
            }

            is ConnectException -> {
                Log.e("AuthViewModel", "Sin conexión a internet")
                AuthState.Error("Sin conexión a internet")
            }

            else -> {
                Log.e("AuthViewModel", "Error desconocido: ${result.exceptionOrNull()}")
                AuthState.Error("Error desconocido")
            }
        }

    fun logout() {
        Repository.logout()
    }
}
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    /*object ErrorUsuarioNoEncontrado : AuthState()
    object ErrorContrasenyaNoValida : AuthState()
    object ErrorNoConexionAInternet : AuthState()
    object ErrorDesconocido : AuthState()*/
    data class Error(val exception: String) : AuthState()
}