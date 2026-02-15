package com.youtube.auto.service

import android.content.Intent
import android.net.Uri
import androidx.car.app.CarAppService
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.model.*
import androidx.car.app.validation.HostValidator
import androidx.core.graphics.drawable.IconCompat
import com.youtube.auto.R

class YouTubeCarAppService : CarAppService() {

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session {
        return YouTubeSession()
    }

    inner class YouTubeSession : Session() {
        override fun onCreateScreen(intent: Intent): Screen {
            return MainScreen(carContext)
        }
    }
}

// Pantalla principal para Android Auto
class MainScreen(carContext: androidx.car.app.CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val listBuilder = ItemList.Builder()

        // Opción: Ir a YouTube
        listBuilder.addItem(
            Row.Builder()
                .setTitle("Abrir YouTube")
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(carContext, R.drawable.ic_play)
                    ).build()
                )
                .setOnClickListener {
                    // Abrir YouTube en el teléfono
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    carContext.startActivity(intent)
                }
                .build()
        )

        // Opción: Tendencias
        listBuilder.addItem(
            Row.Builder()
                .setTitle("Tendencias")
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(carContext, R.drawable.ic_trending)
                    ).build()
                )
                .setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/feed/trending"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    carContext.startActivity(intent)
                }
                .build()
        )

        // Opción: Historial
        listBuilder.addItem(
            Row.Builder()
                .setTitle("Historial")
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(carContext, R.drawable.ic_history)
                    ).build()
                )
                .setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/feed/history"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    carContext.startActivity(intent)
                }
                .build()
        )

        // Opción: Biblioteca
        listBuilder.addItem(
            Row.Builder()
                .setTitle("Mi Biblioteca")
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(carContext, R.drawable.ic_library)
                    ).build()
                )
                .setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/feed/library"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    carContext.startActivity(intent)
                }
                .build()
        )

        // Opción: Buscar
        listBuilder.addItem(
            Row.Builder()
                .setTitle("Buscar")
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(carContext, R.drawable.ic_search)
                    ).build()
                )
                .setOnClickListener {
                    screenManager.push(SearchScreen(carContext))
                }
                .build()
        )

        return ListTemplate.Builder()
            .setSingleList(listBuilder.build())
            .setTitle("YouTube Auto")
            .setHeaderAction(Action.BACK)
            .build()
    }
}

// Pantalla de búsqueda
class SearchScreen(carContext: androidx.car.app.CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        return SearchTemplate.Builder(
            object : SearchTemplate.SearchCallback {
                override fun onSearchTextChanged(searchText: String) {
                    // Actualizar sugerencias
                }

                override fun onSearchSubmitted(searchText: String) {
                    // Realizar búsqueda en YouTube
                    val query = Uri.encode(searchText)
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://m.youtube.com/results?search_query=$query")
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    carContext.startActivity(intent)
                }
            }
        )
            .setHeaderAction(Action.BACK)
            .setSearchHint("Buscar en YouTube...")
            .build()
    }
}
