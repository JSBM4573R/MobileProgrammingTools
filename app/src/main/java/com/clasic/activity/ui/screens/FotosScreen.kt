package com.clasic.activity.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FotosScreen() {
    var likedItems by remember { mutableStateOf(setOf<String>()) }

    val fotos = listOf(
        PhotoItem("https://picsum.photos/400/300", "Paisaje montañoso al atardecer con colores vibrantes"),
        PhotoItem("https://picsum.photos/401/300", "Ciudad moderna con rascacielos imponentes"),
        PhotoItem("https://picsum.photos/402/300", "Playa tropical con aguas cristalinas y palmeras"),
        PhotoItem("https://picsum.photos/403/300", "Bosque nevado en un día de invierno"),
        PhotoItem("https://picsum.photos/404/300", "Desierto con dunas de arena dorada"),
        PhotoItem("https://picsum.photos/405/300", "Aurora boreal en el cielo nocturno estrellado"),
        PhotoItem("https://picsum.photos/406/300", "Cascada en medio de la selva tropical"),
        PhotoItem("https://picsum.photos/407/300", "Atardecer en el campo con siluetas")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Galería de Fotos",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "${fotos.size} imágenes disponibles",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )

                // Filtros rápidos
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { },
                        label = { Text("Todas") }
                    )
                    FilterChip(
                        selected = false,
                        onClick = { },
                        label = { Text("Favoritas") }
                    )
                }
            }
        }

        // Lista de fotos en grid manual (2 columnas)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(fotos.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { photo ->
                        PhotoCard(
                            photo = photo,
                            isLiked = likedItems.contains(photo.url),
                            onLikeClick = {
                                likedItems = if (likedItems.contains(photo.url)) {
                                    likedItems - photo.url
                                } else {
                                    likedItems + photo.url
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Si hay un número impar de items, agregar un espacio vacío
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class PhotoItem(val url: String, val description: String)

@Composable
fun PhotoCard(
    photo: PhotoItem,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen
            AsyncImage(
                model = photo.url,
                contentDescription = photo.description,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay para mejor legibilidad del texto
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                androidx.compose.ui.graphics.Color.Transparent,
                                androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 300f
                        )
                    )
            )

            // Badge de favorito
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = onLikeClick,
                    modifier = Modifier.size(36.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f)
                    )
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Me gusta",
                        tint = if (isLiked) androidx.compose.ui.graphics.Color.Red
                        else androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }

            // Contenido inferior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = photo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color.White,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Acciones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hace 2 días",
                        style = MaterialTheme.typography.labelSmall,
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f)
                    )

                    IconButton(
                        onClick = { /* Share action */ },
                        modifier = Modifier.size(24.dp)
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            tint = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}

// Alternativa más simple si prefieres una lista vertical
@Composable
fun FotosScreenSimple() {
    var likedItems by remember { mutableStateOf(setOf<String>()) }

    val fotos = listOf(
        PhotoItem("https://picsum.photos/400/300", "Paisaje montañoso al atardecer"),
        PhotoItem("https://picsum.photos/401/300", "Ciudad moderna con rascacielos"),
        PhotoItem("https://picsum.photos/402/300", "Playa tropical con palmeras"),
        PhotoItem("https://picsum.photos/403/300", "Bosque nevado en invierno"),
        PhotoItem("https://picsum.photos/404/300", "Desierto con dunas de arena"),
        PhotoItem("https://picsum.photos/405/300", "Aurora boreal en el cielo nocturno")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Galería de Fotos",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "${fotos.size} imágenes en tu galería",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        // Lista vertical simple
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(fotos) { photo ->
                PhotoListItem(
                    photo = photo,
                    isLiked = likedItems.contains(photo.url),
                    onLikeClick = {
                        likedItems = if (likedItems.contains(photo.url)) {
                            likedItems - photo.url
                        } else {
                            likedItems + photo.url
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PhotoListItem(photo: PhotoItem, isLiked: Boolean, onLikeClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            // Imagen
            AsyncImage(
                model = photo.url,
                contentDescription = photo.description,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            // Contenido
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = photo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hace 2 días",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Row {
                        IconButton(
                            onClick = onLikeClick,
                            modifier = Modifier.size(24.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Me gusta",
                                tint = if (isLiked) androidx.compose.ui.graphics.Color.Red
                                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }

                        IconButton(
                            onClick = { /* Share */ },
                            modifier = Modifier.size(24.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartir",
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}