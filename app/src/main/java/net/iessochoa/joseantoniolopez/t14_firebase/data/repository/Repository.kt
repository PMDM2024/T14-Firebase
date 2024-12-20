package net.iessochoa.joseantoniolopez.t14_firebase.data.repository

import net.iessochoa.joseantoniolopez.t14_firebase.data.firebase.AutenticacionFireBase

object Repository {

    suspend fun login(email: String, password: String)=
        AutenticacionFireBase.login(email,password)
    suspend fun register(email: String, password: String)=
        AutenticacionFireBase.register(email,password)
    fun getCurrentUser() = AutenticacionFireBase.getCurrentUser()

    fun logout() = AutenticacionFireBase.logout()
}