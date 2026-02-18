package com.youtube.androidauto.browser.navigation

import android.webkit.WebView
import com.youtube.androidauto.browser.webview.WebViewManager

/**
 * Controlador de navegación web: atrás, adelante, recargar, inicio.
 */
class NavigationController(private val webView: WebView) {
    private val history = mutableListOf<String>()
    private var currentIndex = -1

    fun navigateToUrl(url: String) {
        val normalized = normalizeUrl(url)
        webView.loadUrl(normalized)
        addToHistory(normalized)
    }

    fun goBack(): Boolean {
        return if (webView.canGoBack()) {
            webView.goBack()
            currentIndex = (currentIndex - 1).coerceAtLeast(-1)
            true
        } else {
            false
        }
    }

    fun goForward(): Boolean {
        return if (webView.canGoForward()) {
            webView.goForward()
            currentIndex = (currentIndex + 1).coerceAtMost(history.size - 1)
            true
        } else {
            false
        }
    }

    fun reload() {
        webView.reload()
    }

    fun goHome() {
        navigateToUrl(WebViewManager.HOME_URL)
    }

    fun canGoBack(): Boolean = webView.canGoBack()

    fun canGoForward(): Boolean = webView.canGoForward()

    fun getCurrentUrl(): String? = webView.url

    private fun addToHistory(url: String) {
        if (currentIndex < history.size - 1) {
            history.subList(currentIndex + 1, history.size).clear()
        }
        if (history.isEmpty() || history.last() != url) {
            history.add(url)
            currentIndex = history.size - 1
        }
    }

    private fun normalizeUrl(input: String): String {
        val trimmed = input.trim()
        return when {
            trimmed.contains(".") && !trimmed.startsWith("http") -> "https://$trimmed"
            trimmed.startsWith("http://") || trimmed.startsWith("https://") -> trimmed
            trimmed.contains(" ") -> "https://www.youtube.com/results?search_query=${trimmed.replace(" ", "+")}"
            else -> "https://www.youtube.com/results?search_query=$trimmed"
        }
    }
}
