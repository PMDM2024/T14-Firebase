package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.starup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.joseantoniolopez.t14_firebase.R

@Preview(showBackground = true)
@Composable
fun StarUpScreen(
    navigateToLogin: () -> Unit = {},
    navigateToSingUp: () -> Unit = {}
){
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Brush.verticalGradient(listOf(Color.LightGray, Color.Yellow))),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Spacer(modifier = Modifier.weight(1f))
    Spacer(modifier = Modifier.weight(1f))
    Text(
        text = "T14 Firebase",
        style = MaterialTheme.typography.displayLarge,
    )
    Spacer(modifier = Modifier.weight(1f))
    Image(
        painter = painterResource(id = R.drawable.logo_ochoa),
        contentDescription = "Logo",
        modifier = Modifier.size(300.dp)
    )
    Spacer(modifier = Modifier.weight(1f))
    Button(
        onClick = navigateToSingUp
        ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
    ) {
        Text(text = "Registrarse")
    }
    Button(
        onClick = navigateToLogin,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
    ) {
        Text(text = "Iniciar Sesión")
    }
    Spacer(modifier = Modifier.weight(1f))
}
}