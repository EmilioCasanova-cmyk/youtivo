#!/bin/bash

# Script de compilación para YouTube Auto App
# Uso: ./build.sh [debug|release]

set -e

BUILD_TYPE="${1:-debug}"
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "=================================="
echo "  YouTube Auto - Build Script"
echo "=================================="
echo ""
echo "Build type: $BUILD_TYPE"
echo "Project directory: $PROJECT_DIR"
echo ""

# Verificar Java
if ! command -v java &> /dev/null; then
    echo "ERROR: Java no está instalado"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Java version: $JAVA_VERSION"

# Dar permisos al wrapper
chmod +x "$PROJECT_DIR/gradlew"

# Navegar al directorio del proyecto
cd "$PROJECT_DIR"

# Limpiar build anterior
echo ""
echo "Limpiando build anterior..."
./gradlew clean

# Compilar
echo ""
echo "Compilando APK ($BUILD_TYPE)..."
if [ "$BUILD_TYPE" == "release" ]; then
    ./gradlew assembleRelease
    OUTPUT_DIR="$PROJECT_DIR/app/build/outputs/apk/release"
    APK_NAME="app-release-unsigned.apk"
else
    ./gradlew assembleDebug
    OUTPUT_DIR="$PROJECT_DIR/app/build/outputs/apk/debug"
    APK_NAME="app-debug.apk"
fi

# Verificar resultado
echo ""
echo "=================================="
if [ -f "$OUTPUT_DIR/$APK_NAME" ]; then
    echo "  COMPILACIÓN EXITOSA!"
    echo "=================================="
    echo ""
    echo "APK generado:"
    echo "  $OUTPUT_DIR/$APK_NAME"
    echo ""
    ls -lh "$OUTPUT_DIR/$APK_NAME"
    echo ""
    echo "Para instalar:"
    echo "  adb install $OUTPUT_DIR/$APK_NAME"
else
    echo "  ERROR: No se encontró el APK"
    echo "=================================="
    exit 1
fi
