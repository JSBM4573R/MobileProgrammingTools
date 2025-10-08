# Aplicación Móvil Android - Proyecto Educativo

## Descripción

Desarrollo de software móvil siguiendo las etapas del ciclo de vida del desarrollo de software: análisis, diseño, implementación, pruebas y despliegue.  
La aplicación está pensada para consolidar conocimientos sobre interfaces modulares mediante actividades y fragmentos, aplicando principios de usabilidad y buenas prácticas en programación móvil.

El desarrollo se realiza en **Kotlin** usando **Android Studio**, integrando componentes nativos como vistas de texto, botones, listas, widgets, manejo de eventos, imágenes, videos y navegación web.  
La interfaz se compone de un menú lateral izquierdo y un área derecha para mostrar dinámicamente la información asociada a cada selección.

Este proyecto representa una oportunidad formativa completa, integrando teoría y práctica, y preparando a los participantes para retos del desarrollo móvil en contextos académicos y profesionales.

---

## Objetivo General

Diseñar e implementar una aplicación móvil para Android que integre actividades y fragmentos interactivos con distintos elementos de interfaz como imágenes, listas, controles de video, navegación web y botones, demostrando la correcta utilización de componentes nativos de Android y afianzando competencias prácticas en desarrollo móvil.

---

## Objetivos Específicos

1. Implementar una interfaz en Android basada en actividades y fragmentos, garantizando una distribución adecuada de contenidos y navegación intuitiva.
2. Desarrollar funcionalidades interactivas: visualización de perfiles, exploración de imágenes, reproducción de videos, carga dinámica de páginas web y manejo de botones.
3. Utilizar Android Studio como herramienta principal de desarrollo, fomentando el aprendizaje práctico de tecnologías vigentes en la industria móvil.

---

## Análisis de Requerimientos

### Requerimientos Funcionales

- La aplicación debe estar compuesta por una actividad principal (`MainActivity`) que integre dos fragmentos en pantalla (izquierda y derecha).
- El fragmento izquierdo presenta un menú con las opciones:
  - Perfil
  - Fotos
  - Video
  - Web
  - Botones
- El fragmento derecho muestra contenido dinámico según la opción seleccionada:
  - **Perfil:** información de una persona (foto, estudios, experiencia) con desplazamiento vertical.
  - **Fotos:** lista de imágenes con scroll y descripción al seleccionarlas.
  - **Video:** reproducción de video con controles de play, pausa y stop.
  - **Web:** caja de texto para digitar una URL y cargar la página en un navegador embebido.
  - **Botones:** ejemplos interactivos de botones (mostrar mensajes, cambiar colores, etc.).
- Desplazamiento dentro de los fragmentos mediante scroll.
- Navegación entre opciones actualiza en tiempo real el contenido del fragmento derecho.

### Requerimientos No Funcionales

- **Compatibilidad:** Android 8.0 (Oreo) o superior.
- **Usabilidad:** diseño claro siguiendo Material Design.
- **Rendimiento:** aplicación fluida sin bloqueos al cambiar entre opciones.
- **Mantenibilidad:** código estructurado aplicando buenas prácticas (separación de actividades y fragmentos).
- **Portabilidad:** ejecutable en diferentes tamaños de pantalla (teléfonos y tablets).

---

## Instalación

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Compila y ejecuta en un emulador o dispositivo físico.

## Usabilidad

- Navega por el menú lateral para acceder a cada sección.
- En Perfil: desplázate verticalmente para ver la información.
- En Fotos: explora la galería, marca favoritas y comparte imágenes.
- En Video: reproduce, pausa y detén el video.
- En Web: ingresa una URL y navega en la web embebida.
- En Botones: interactúa con ejemplos de botones y observa sus efectos.

## Imagenes

### Menu
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1B8cvcZzrUr_1Zt1236L1cPYozDk-pYIR" alt="Menu"/>
  </p>
  
### Perfil
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1XtZV3ZPi6iR_TxKeyoqOO81vQ8Jk0fXr" alt="Perfil"/>
  </p>

### Fotos
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1kEF-8-Eye1totcldYusau3QwEiyD6sYi" alt="Perfil"/>
  </p>

### Video
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1ANSE2_pAz69JkeL2vyZa7gdvt_Lvbg9x" alt="Perfil"/>
  </p>

### Web
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1QSGLWUihx6_FcxZEVdiYy05SeRW7RoJQ" alt="Perfil"/>
  </p>

### Botones
<p align="center">
  <img heigth="200" src="https://drive.google.com/uc?export=view&id=1r6uqqeEF6w2306RnBTRyhtSPY26sajl0" alt="Perfil"/>
  </p>



# Características Principales

    Interfaz moderna basada en Material Design 3

    Soporte de tema claro y oscuro automático

    Navegación mediante panel lateral (drawer)

    Integración de imágenes, video y navegación web

    Componentes interactivos y diseño adaptable
    
# Pantallas Implementadas

    Perfil: Presentación de información personal con imagen y descripción.

    Fotos: Galería de imágenes en cuadrícula con visualización básica.

    Video: Reproductor integrado con ExoPlayer y controles personalizados.

    Web: Navegador web interno con barra de búsqueda y controles de navegación.

    Botones: Ejemplos de distintos estilos y variantes de botones.

Tecnologías Utilizadas
# Lenguajes y Frameworks

    Kotlin

    Jetpack Compose

    Material Design 3

# Principales Dependencias
    // Jetpack Compose
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:1.6.2")
    implementation("androidx.compose.material3:material3:1.2.1")

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Imágenes
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Video
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")

    // WebView
    implementation("com.google.accompanist:accompanist-webview:0.30.1")

# Requisitos de Entorno

    - Android Studio: Hedgehog o superior

    - SDK mínimo: API 24 (Android 7.0)

    - SDK de compilación: API 35

    - Gradle: 8.0+

    - JVM target: 11

# Estructura del Proyecto
app/
├── src/main/java/com/clasic/activity/
│   ├── MainActivity.kt
│   ├── ui/
│   │   ├── navigation/
│   │   │   ├── NavGraph.kt
│   │   ├── screens/
│   │   │   ├── ProfileScreen.kt
│   │   │   ├── PhotoScreen.kt
│   │   │   ├── VideoScreen.kt
│   │   │   ├── WebScreen.kt
│   │   │   └── ButtonScreen.kt
│   │   └── theme/
│   │       ├── Theme.kt
│   │       └── Typography.kt
└── build.gradle.kts

Instalación y Ejecución
# Requisitos Previos

    Android Studio configurado correctamente

    SDK de Android API 35

    Emulador o dispositivo físico con Android 7.0 o superior

# Pasos

    Clonar o descargar el repositorio

    Abrir el proyecto en Android Studio

    Esperar la sincronización de dependencias

    Ejecutar en un emulador o dispositivo

# Comandos Útiles
    # Compilar en modo debug
    ./gradlew assembleDebug

    # Limpiar el proyecto
    ./gradlew clean

# Navegación

    La navegación se implementó con Navigation Compose.
    Se utiliza un drawer lateral para moverse entre las distintas pantallas, y las rutas están definidas en una clase sealed para mayor claridad y seguridad en la navegación.

Ejemplo:

sealed class Screens(val route: String) {
    object Perfil : Screens("perfil")
    object Fotos : Screens("fotos")
    object Video : Screens("video")
    object Web : Screens("web")
    object Botones : Screens("botones")
}

# Decisiones de Diseño

    UI declarativa con Jetpack Compose (sin XML).

    Estado reactivo con remember y mutableState.

    Navegación fluida y declarativa.

    Estructura modular y clara para separar pantallas y navegación.

    Consistencia visual siguiendo Material Design 3.