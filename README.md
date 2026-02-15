# YouTube Auto - App para Android Auto

Aplicación Android que permite reproducir videos de YouTube en Android Auto con soporte para reproducción en segundo plano.

## Características

- **WebView con YouTube**: Utiliza la versión móvil de YouTube en un WebView optimizado
- **Reproducción en segundo plano**: El audio continúa reproduciéndose incluso cuando la app está en segundo plano
- **Android Auto compatible**: Aparece en el menú interactivo de aplicaciones del auto
- **Universal ARM**: Compatible con armeabi-v7a, arm64-v8a, x86 y x86_64
- **Controles de medios**: Soporte para controles de auriculares y Android Auto

## Requisitos

- Android 7.0 (API 24) o superior
- Android Auto instalado en el teléfono
- Conexión USB o Bluetooth al vehículo

## Estructura del Proyecto

```
YouTubeAutoApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/youtube/auto/
│   │   │   ├── YouTubeAutoApp.kt           # Clase Application
│   │   │   ├── service/
│   │   │   │   ├── BackgroundPlaybackService.kt      # Servicio de reproducción en segundo plano
│   │   │   │   ├── YouTubeMediaBrowserService.kt     # MediaBrowser para Android Auto
│   │   │   │   └── YouTubeCarAppService.kt           # Servicio Car App para Android Auto
│   │   │   ├── ui/
│   │   │   │   └── MainActivity.kt          # Actividad principal con WebView
│   │   │   └── receiver/
│   │   │       └── MediaButtonReceiver.kt   # Receptor de botones de medios
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   └── xml/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Compilación

### Usando Android Studio

1. Abre el proyecto en Android Studio
2. Sincroniza el proyecto con Gradle
3. Selecciona Build > Build Bundle(s) / APK(s) > Build APK(s)
4. El APK se generará en `app/build/outputs/apk/debug/`

### Usando línea de comandos

```bash
# Navegar al directorio del proyecto
cd YouTubeAutoApp

# Dar permisos al wrapper de Gradle (Linux/Mac)
chmod +x gradlew

# Compilar APK de debug
./gradlew assembleDebug

# O compilar APK de release
./gradlew assembleRelease
```

## Instalación

### Instalación manual (APK)

1. Habilita "Orígenes desconocidos" en Configuración > Seguridad
2. Transfiere el APK al teléfono
3. Instala el APK

### Usando ADB

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Configuración de Android Auto

1. Instala la app en tu teléfono
2. Abre Android Auto
3. Ve a Configuración > Mostrar todas las apps
4. Habilita "YouTube Auto"

## Permisos necesarios

- **INTERNET**: Acceso a YouTube
- **FOREGROUND_SERVICE**: Reproducción en segundo plano
- **WAKE_LOCK**: Mantener CPU activa durante reproducción

## Notas importantes

- La app utiliza WebView para cargar YouTube móvil (m.youtube.com)
- Se inyecta JavaScript para prevenir que YouTube pause el video en segundo plano
- El servicio de reproducción en segundo plano usa MediaSession para controles
- Android Auto requiere que el teléfono esté conectado al vehículo

## Solución de problemas

### La app no aparece en Android Auto
- Asegúrate de que Android Auto esté actualizado
- Ve a Configuración de Android Auto > Personalizar launcher > Habilitar YouTube Auto

### No hay audio en segundo plano
- Verifica que la notificación de reproducción esté activa
- Comprueba los permisos de la app

### El video se pausa al minimizar
- Asegúrate de que el servicio de segundo plano esté iniciado
- Verifica que la batería no esté optimizada para esta app

## Licencia

Este proyecto es de código abierto. Úsalo bajo tu propia responsabilidad.

## Descargo de responsabilidad

Esta app es un wrapper de YouTube. No está afiliada ni respaldada por Google/YouTube. 
Úsala de acuerdo con los términos de servicio de YouTube.
