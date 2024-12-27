package com.example.mywebradioapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem

class MainActivity : AppCompatActivity() {

    private lateinit var exoPlayer: ExoPlayer

    // Liste d'URL
    private val radioStations = listOf(
        "https://radio-url1.com/stream",
        "https://radio-url2.com/stream",
        // Ajoute toutes tes URLs ici
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()

        // Récupère le LinearLayout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        // Génère un bouton par station
        radioStations.forEachIndexed { index, url ->
            val button = Button(this).apply {
                text = "Station ${index + 1}"
                textSize = 24f
                setOnClickListener {
                    playRadio(url)
                }
            }
            linearLayout.addView(button)
        }
    }

    private fun playRadio(url: String) {
        exoPlayer.stop()
        exoPlayer.clearMediaItems()

        val mediaItem = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
