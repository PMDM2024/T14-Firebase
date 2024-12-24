package net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.joseantoniolopez.t14_firebase.data.repository.Repository

class PrincipalViewModel: ViewModel()
{
    private val _uiState = MutableStateFlow(UiStatePrincipal(usuario = Repository.getCurrentUser()))
    val uiState: StateFlow<UiStatePrincipal> = _uiState.asStateFlow()

    fun cerrarSesion() {
        Repository.logout()
    }
    /*fun getUserEmail(): String  {
        val usuario= Repository.getCurrentUser()
        return usuario.email
    }
    fun getUserName(): String  {
        val usuario= Repository.getCurrentUser()
        return usuario.nombre
    }*/
}
