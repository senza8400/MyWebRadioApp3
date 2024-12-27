package com.example.mywebradioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {

    private lateinit var exoPlayer: ExoPlayer

    // Ta liste d'environ 30 URL de webradios
    private val radioStations = listOf(
        "https://onefm.ice.infomaniak.ch/onefm-high.aac",
        "https://mlaradio.ice.infomaniak.ch/mlaradio.mp3",
        // Ajoute ici tes autres URLs...
        // ...
        // "https://autrewebradio.com/stream",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()

        // Récupère le LinearLayout du layout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        // Pour chaque URL, on crée dynamiquement un gros bouton
        radioStations.forEachIndexed { index, url ->
            val button = Button(this).apply {
                text = "Station ${index + 1}"
                textSize = 24f    // Ajuste pour un texte plus gros
                setOnClickListener {
                    playRadio(url)
                }
            }
            // Ajoute le bouton au LinearLayout
            linearLayout.addView(button)
        }
    }

    /**
     * Fonction pour lancer la lecture d'un flux audio via ExoPlayer
     */
    private fun playRadio(url: String) {
        // Stoppe la lecture en cours, s'il y en a une
        exoPlayer.stop()
        // Vide la liste d'éventuels mediaItems déjà chargés
        exoPlayer.clearMediaItems()

        // Crée et associe le nouveau flux à lire
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()

        // Lance la lecture
        exoPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        // Libère les ressources d'ExoPlayer
        exoPlayer.release()
    }
}
