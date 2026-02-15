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
