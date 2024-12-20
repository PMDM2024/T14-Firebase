package net.iessochoa.joseantoniolopez.t14_firebase.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import net.iessochoa.joseantoniolopez.t14_firebase.R

@Composable
fun Logo(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = modifier
    )
}