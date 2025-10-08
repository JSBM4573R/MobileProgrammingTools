package com.clasic.activity.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage

data class PhotoItem(
    val url: String,
    val description: String
)

@Composable
fun FotosScreen() {
    var likedItems by remember { mutableStateOf(setOf<String>()) }
    var selectedPhoto by remember { mutableStateOf<PhotoItem?>(null) }

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

    // Dialog para mostrar la descripción completa
    selectedPhoto?.let { photo ->
        PhotoDescriptionDialog(
            photo = photo,
            isLiked = likedItems.contains(photo.url),
            onLikeClick = {
                likedItems = if (likedItems.contains(photo.url)) {
                    likedItems - photo.url
                } else {
                    likedItems + photo.url
                }
            },
            onDismiss = { selectedPhoto = null }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Toca cualquier foto para ver su descripción",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // LazyColumn scrollable con weight
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(fotos.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { photo ->
                        PhotoCard(
                            photo = photo,
                            isLiked = likedItems.contains(photo.url),
                            onPhotoClick = { selectedPhoto = photo },
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
                    if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun PhotoCard(
    photo: PhotoItem,
    isLiked: Boolean,
    onPhotoClick: () -> Unit,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onPhotoClick() }
        ) {
            AsyncImage(
                model = photo.url,
                contentDescription = photo.description,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay sutil
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                androidx.compose.ui.graphics.Color.Transparent,
                                androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.3f)
                            ),
                            startY = 300f
                        )
                    )
            )

            // Icono de like
            IconButton(
                onClick = {
                    onLikeClick()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(36.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Me gusta",
                    tint = if (isLiked) androidx.compose.ui.graphics.Color.Red
                    else androidx.compose.ui.graphics.Color.Gray
                )
            }

            // Indicador de que tiene descripción (icono de info)
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Ver descripción",
                    tint = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(20.dp)
                )
            }

            // Texto indicador para tocar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Toca para ver detalles",
                    style = MaterialTheme.typography.labelSmall,
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun PhotoDescriptionDialog(
    photo: PhotoItem,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    AsyncImage(
                        model = photo.url,
                        contentDescription = photo.description,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(40.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.5f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }

                    // Botón de like en el dialog
                    IconButton(
                        onClick = onLikeClick,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .size(40.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.5f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Me gusta",
                            tint = if (isLiked) androidx.compose.ui.graphics.Color.Red
                            else androidx.compose.ui.graphics.Color.White
                        )
                    }
                }

                // Contenido del dialog
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = photo.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones de acción
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = onDismiss
                        ) {
                            Text("Cerrar")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                // Acción para compartir
                                onDismiss()
                            }
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Compartir")
                        }
                    }
                }
            }
        }
    }
}

// Versión simplificada sin el lineHeight si prefieres
@Composable
fun PhotoDescriptionDialogSimple(
    photo: PhotoItem,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Imagen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    AsyncImage(
                        model = photo.url,
                        contentDescription = photo.description,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Botón de cerrar
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .size(36.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.5f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }

                // Contenido
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Fila con like y título
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Descripción",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        IconButton(
                            onClick = onLikeClick,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Me gusta",
                                tint = if (isLiked) androidx.compose.ui.graphics.Color.Red
                                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Descripción
                    Text(
                        text = photo.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = onDismiss
                        ) {
                            Text("Aceptar")
                        }
                    }
                }
            }
        }
    }
}