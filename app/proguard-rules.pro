# ProGuard rules for YouTube Auto App
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Keep WebView JavaScript interface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep MediaBrowserService
-keep class androidx.media.MediaBrowserServiceCompat { *; }
-keep class androidx.media3.session.MediaSessionService { *; }

# Keep Android Auto
-keep class androidx.car.app.** { *; }
