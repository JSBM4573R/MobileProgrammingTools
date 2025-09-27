package com.clasic.activity.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.*

@Composable
fun WebScreen() {
    var url by remember { mutableStateOf("https://www.google.com") }
    var input by remember { mutableStateOf(url) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Ingrese URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { url = input },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Abrir")
        }

        Spacer(Modifier.height(16.dp))

        WebView(
            state = rememberWebViewState(url),
            modifier = Modifier.fillMaxSize()
        )
    }
}
