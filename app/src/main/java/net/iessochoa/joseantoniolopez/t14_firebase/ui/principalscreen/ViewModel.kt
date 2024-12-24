package net.iessochoa.joseantoniolopez.t14_firebase.ui.principalscreen

import androidx.lifecycle.ViewModel
import net.iessochoa.joseantoniolopez.t14_firebase.data.repository.Repository

class PrincipalViewModel: ViewModel()
{
   /* private val _uiState = MutableStateFlow(UiStatePrincipal(email = getUserEmail()))
    val uiState: StateFlow<UiStatePrincipal> = _uiState.asStateFlow()*/

    fun logout() {
        Repository.logout()
    }
    fun getUserEmail(): String  {
        val usuario= Repository.getCurrentUser()
        return " ${ usuario.email}"
    }
    fun getUserName(): String  {
        val usuario= Repository.getCurrentUser()
        return "${usuario.nombre}"
    }
}
