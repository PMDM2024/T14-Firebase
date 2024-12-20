package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
 fun PasswordOutLinedTextField(label: String="Contraseña",
                               password: String,
                                      onValueChange: (String) -> Unit={},
                                      modifier: Modifier = Modifier.fillMaxWidth()
)
{
    /*var password1 = password
    var isPasswordVisible1 = isPasswordVisible*/
    var isPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (isPasswordVisible) ImageVector.vectorResource(id = R.drawable.ic_visibility_off)
                else ImageVector.vectorResource(id = R.drawable.ic_visibility)

            val description =
                if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"

            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = image, contentDescription = description)
            }
        },
        modifier = modifier
    )
}
