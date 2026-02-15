@echo off
REM Script de compilación para YouTube Auto App (Windows)
REM Uso: build.bat [debug|release]

setlocal enabledelayedexpansion

set "BUILD_TYPE=%~1"
if "%BUILD_TYPE%"=="" set "BUILD_TYPE=debug"

set "PROJECT_DIR=%~dp0"

echo ==================================
echo   YouTube Auto - Build Script
echo ==================================
echo.
echo Build type: %BUILD_TYPE%
echo Project directory: %PROJECT_DIR%
echo.

REM Verificar Java
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java no esta instalado
    exit /b 1
)

REM Navegar al directorio del proyecto
cd /d "%PROJECT_DIR%"

REM Limpiar build anterior
echo.
echo Limpiando build anterior...
call gradlew.bat clean
if errorlevel 1 (
    echo ERROR: Fallo al limpiar
    exit /b 1
)

REM Compilar
echo.
echo Compilando APK (%BUILD_TYPE%)...
if "%BUILD_TYPE%"=="release" (
    call gradlew.bat assembleRelease
    set "OUTPUT_DIR=%PROJECT_DIR%app\build\outputs\apk\release"
    set "APK_NAME=app-release-unsigned.apk"
) else (
    call gradlew.bat assembleDebug
    set "OUTPUT_DIR=%PROJECT_DIR%app\build\outputs\apk\debug"
    set "APK_NAME=app-debug.apk"
)

if errorlevel 1 (
    echo ERROR: Fallo la compilacion
    exit /b 1
)

REM Verificar resultado
echo.
echo ==================================
if exist "%OUTPUT_DIR%\%APK_NAME%" (
    echo   COMPILACION EXITOSA!
    echo ==================================
    echo.
    echo APK generado:
    echo   %OUTPUT_DIR%\%APK_NAME%
    echo.
    dir "%OUTPUT_DIR%\%APK_NAME%"
    echo.
    echo Para instalar:
    echo   adb install "%OUTPUT_DIR%\%APK_NAME%"
) else (
    echo   ERROR: No se encontro el APK
    echo ==================================
    exit /b 1
)

endlocal
