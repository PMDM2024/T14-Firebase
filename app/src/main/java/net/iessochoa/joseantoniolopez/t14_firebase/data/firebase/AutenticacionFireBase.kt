package net.iessochoa.joseantoniolopez.t14_firebase.data.firebase

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

object AutenticacionFireBase {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth
                .signInWithEmailAndPassword(email, password).await()
            Result.success(Unit) // Login exitoso
            /*firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }*/
        } catch (e: Exception) {
            Result.failure(e) // Manejo de error
        }
    }
    suspend fun register(email: String, password: String, displayName: String=""): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
           //registramos el nombre de usuario
            firebaseAuth.currentUser!!.updateProfile(
                UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(displayName)
                    .build()
            )?.await()
            Result.success(Unit) // Registro exitoso
        } catch (e: Exception) {
            Result.failure(e) // Manejo de error
        }
    }

    fun getCurrentUser() = firebaseAuth.currentUser

    fun logout() {
        firebaseAuth.signOut()
    }
}