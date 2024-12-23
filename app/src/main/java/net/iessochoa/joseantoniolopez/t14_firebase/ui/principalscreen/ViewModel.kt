package net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.joseantoniolopez.t14_firebase.data.repository.Repository
import net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.AuthState

class PrincipalViewModel: ViewModel()
{
    private val _uiState = MutableStateFlow(UiStatePrincipal(email = getUserEmail()))
    val uiState: StateFlow<UiStatePrincipal> = _uiState.asStateFlow()

    fun logout() {
        Repository.logout()
    }
    fun getUserEmail(): String  {
        val user= Repository.getCurrentUser()
        return "${user?.displayName} - ${ user?.email}"
    }
}
