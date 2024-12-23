package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import net.iessochoa.joseantoniolopez.t14_firebase.R
/**
 * Composable que representa un campo de texto para ingresar contraseñas.
 * Incluye un ícono representativo a la izquierda y una funcionalidad para mostrar u ocultar la contraseña.
 *
 * @param label Etiqueta que describe el propósito del campo de texto. Valor predeterminado: "Contraseña".
 * @param password Texto actual que se muestra en el campo de texto.
 * @param onValueChange Callback que se ejecuta cuando el texto cambia, proporcionando el nuevo valor.
 * @param modifier Modificador para personalizar el diseño y comportamiento del campo de texto. Por defecto, ocupa todo el ancho disponible.
 */
@Composable
fun PasswordOutLinedTextField(
    label: String = "Contraseña",
    password: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    // Estado local que controla la visibilidad de la contraseña
    var isPasswordVisible by remember { mutableStateOf(false) }

    // Campo de texto con ícono representativo y funcionalidad de visibilidad de contraseña
    OutlinedTextField(
        value = password, // Texto mostrado en el campo
        onValueChange = onValueChange, // Callback para manejar cambios de texto
        label = { Text(label) }, // Etiqueta mostrada sobre el campo
        visualTransformation = if (isPasswordVisible) VisualTransformation.None // Texto visible
        else PasswordVisualTransformation(), // Texto transformado en puntos o asteriscos

        // Ícono representativo de contraseña en el lado izquierdo
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock, // Ícono de candado
                contentDescription = "Contraseña", // Descripción para accesibilidad
            )
        },

        // Ícono interactivo para alternar la visibilidad de la contraseña en el lado derecho
        trailingIcon = {
            val image = if (isPasswordVisible)
                ImageVector.vectorResource(id = R.drawable.ic_visibility_off) // Ícono "ocultar contraseña"
            else
                ImageVector.vectorResource(id = R.drawable.ic_visibility) // Ícono "mostrar contraseña"

            val description = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"

            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = image, contentDescription = description)
            }
        },

        // Modificador para personalizar el diseño del campo
        modifier = modifier
    )
}
fun esCorrectoEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}