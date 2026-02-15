YouTivo - APK y uso

Descarga directa del APK de debug:

https://raw.githubusercontent.com/EmilioCasanova-cmyk/youtivo/main/app-debug.apk

Instalación en Android:

1. Descarga el archivo desde el enlace anterior en tu dispositivo Android.
2. Asegúrate de habilitar "Instalar aplicaciones desde fuentes desconocidas" para el navegador/gestor de archivos.
3. Abre el archivo `app-debug.apk` y confirma la instalación.

Notas:

- Este es un build de debug; no está firmado para distribución pública.

Solución de problemas y pasos recomendados (Android Auto y reproducción en segundo plano):

1. La app debe aparecer en Android Auto: asegúrate de que Android Auto esté actualizado en tu teléfono. En la app Android Auto ve a `Configuración > Personalizar launcher` y habilita `YouTube Auto` si aparece.
2. Si la app no aparece como media app, la rama `fix/android-auto-background-audio` contiene cambios para exponer la categoría `MEDIA` en el servicio `CarAppService`.
3. Reproducción en segundo plano y notificación: la app crea un servicio de primer plano con una notificación persistente; comprueba que la notificación de reproducción esté activa.
4. Sin audio en segundo plano: verifica permisos y que la notificación haya sido aceptada. En algunos dispositivos hay que desactivar la optimización de batería manualmente para la app.
5. El video se pausa al minimizar: la app evita pausar `WebView` en `onPause()` para mantener audio; si tu dispositivo sigue deteniendo la reproducción, desactiva optimizaciones de batería para la app.

- Si prefieres obtener el APK desde las Actions, el repositorio contiene un workflow en `.github/workflows/android-build.yml`.
