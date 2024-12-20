package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Encabezado(
    onBack: () -> Unit = {},
    titulo: String = "",
    modifier: Modifier=Modifier
){
    Column(modifier = modifier) {
        Spacer(
            modifier = Modifier.size(
                40.dp
            )
        )
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Atrás",
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Start)
                .clickable { onBack() },

            )
        Spacer(modifier = Modifier.weight(1f))
        Logo(
            modifier = Modifier.size(height = 200.dp, width = 300.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = titulo,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}