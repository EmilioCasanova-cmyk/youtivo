# YouTube Web Browser para Android Auto

Aplicación Android Auto que integra un navegador web para acceder a YouTube con bloqueo automático de video cuando el vehículo está en movimiento.

## Descarga

[Descargar APK](https://github.com/EmilioCasanova-cmyk/youtivo/releases/download/v1.0/app-debug.apk)


## Requisitos

- Android Studio Ladybug (2024.2.1) o superior
- JDK 17
- minSdk 29, targetSdk 34

## Estructura del proyecto (Arquitectura MVP)

- **car/** – Android Auto: `YouTubeBrowserCarAppService`, `YouTubeBrowserSession`, `WebViewTemplateScreen`
- **webview/** – `WebViewManager`, `VideoControlScripts`, `VideoBlockerOverlay`
- **vehicle/** – `VehicleStateMonitor`, `VehicleState`
- **safety/** – `SafetyController`
- **navigation/** – `NavigationController`
- **storage/** – `SessionCookieManager` (cookies)
- **error/** – `AppState`, `ErrorHandler`
- **ui/** – `MainBrowserActivity`
- **di/** – Hilt (inyección de dependencias)

## Cómo compilar

1. Abre el proyecto en Android Studio.
2. Sincroniza Gradle (File → Sync Project with Gradle Files).
3. Genera el wrapper si hace falta: `gradle wrapper` (o usa el que incluye Android Studio).
4. Ejecuta en emulador o dispositivo: Run → Run 'app'.

## Cómo probar en Android Auto

- **Emulador:** Crea un AVD con “Android Automotive” o usa el emulador de Android Auto.
- **Dispositivo real:** Conecta el teléfono al coche con Android Auto y abre la app desde la pantalla del vehículo.

## Iconos de la app

En dispositivos con API &lt; 26 hay que añadir iconos en `res/mipmap-hdpi`, `mipmap-mdpi`, etc. (ic_launcher.png, ic_launcher_round.png). En API 26+ se usan los adaptive icons en `mipmap-anydpi-v26`.

## Seguridad vial

- El video se bloquea cuando el estado del vehículo es MOVING o cuando no hay sensores (UNKNOWN).
- En entorno sin coche (teléfono solo) el monitor devuelve UNKNOWN y el video permanece bloqueado por defecto.
- Para usar sensores reales en el coche, implementa `VehicleStateMonitor` con `CarContext` y la API de sensores del vehículo (p. ej. Car App Library hardware).

## Documento de arquitectura

Basado en *Arquitectura_YouTube_AndroidAuto (1).md* (v2.0, 14 feb 2026).
