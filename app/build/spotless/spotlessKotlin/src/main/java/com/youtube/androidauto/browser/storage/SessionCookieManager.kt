package com.youtube.androidauto.browser.storage

import android.webkit.CookieManager
import android.webkit.WebView
import timber.log.Timber

/**
 * Gestión de cookies para persistir sesión de YouTube entre reinicios.
 */
class SessionCookieManager(private val webView: WebView? = null) {
    private val cookieManager = CookieManager.getInstance()

    init {
        cookieManager.setAcceptCookie(true)
        webView?.let { cookieManager.setAcceptThirdPartyCookies(it, true) }
    }

    fun saveCookies() {
        cookieManager.flush()
    }

    fun clearCookies(onResult: (Boolean) -> Unit = {}) {
        cookieManager.removeAllCookies { success ->
            Timber.d("Cookies cleared: $success")
            onResult(success)
        }
    }

    fun getCookiesForDomain(domain: String): String? = cookieManager.getCookie(domain)
}
