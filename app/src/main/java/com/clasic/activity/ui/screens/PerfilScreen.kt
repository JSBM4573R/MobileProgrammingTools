package com.clasic.activity.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

@Composable
fun PerfilScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            AsyncImage(
                model = "https://i.pravatar.cc/300",
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("Correo: ejemplo@gmail.com")
            Text("Teléfono: +57 3144642906")
            Text("Estudios: Ingeniería de Software")
            Text("Experiencia: Desarrollo Fullstack")
            Text("Descripción: Apasionado por la tecnología ")
        }
    }
}