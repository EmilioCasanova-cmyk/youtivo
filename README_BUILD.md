Instrucciones para compilar (GitHub Actions)

````markdown
Instrucciones para compilar (GitHub Actions)

1. Subir los cambios a tu repositorio remoto:

```powershell
git add .
git commit -m "Add CI workflow for building APK"
git push origin HEAD
```

2. Ejecutar el workflow desde GitHub:

- Ve a la pestaña **Actions** en tu repositorio.
- Selecciona **Build Debug APK** y pulsa **Run workflow** (o ejecuta mediante `workflow_dispatch`).

También puedes lanzar el workflow con la CLI `gh`:

```bash
gh workflow run android-build.yml
```

3. Descargar el APK:

- Cuando la ejecución termine, abre la ejecución del workflow y en la sección **Artifacts** descarga `app-debug-apk`.

4. Notas y alternativas:

- Ruta del workflow: [.github/workflows/android-build.yml](.github/workflows/android-build.yml)
- Si prefieres compilar localmente, instala Android SDK y ejecuta en Windows PowerShell:

```powershell
.\gradlew.bat clean assembleDebug
# APK: app\build\outputs\apk\debug\app-debug.apk
```

- El workflow instala herramientas y usa `android-33` y `build-tools;33.0.2`. Cambia estas versiones si tu proyecto requiere otras.

Lista de problemas conocidos

- La app no aparece en Android Auto: comprueba que Android Auto esté actualizado y en la app Android Auto ve a `Configuración > Personalizar launcher` para habilitar `YouTube Auto`.
- No hay audio en segundo plano: verifica que la notificación de reproducción esté activa, que el servicio de primer plano esté iniciado y que la app tenga permiso para ejecutarse en background; desactiva la optimización de batería si es necesario.
- El video se pausa al minimizar: la app intenta evitar pausar `WebView` en `onPause()` pero algunos fabricantes matan procesos; añade la app a la lista blanca de optimización de batería.
- Permisos y notificaciones: revisa `INTERNET`, `WAKE_LOCK`, permisos de servicio en primer plano y que el usuario haya permitido notificaciones y controles multimedia.
- Android Auto y manifest: asegúrate que `automotive_app_desc.xml` incluya `<uses name="media"/>` y que el servicio CarApp declare la categoría `androidx.car.app.category.MEDIA`.
````
