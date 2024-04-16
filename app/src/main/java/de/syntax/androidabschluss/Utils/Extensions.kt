package de.syntax.androidabschluss.Utils

import android.widget.ImageView
import com.bumptech.glide.Glide

// Erweiterungsfunktion für ImageView, um das Laden von Bildern mit Glide zu vereinfachen.
fun ImageView.glideImageSet(url: String) {
    Glide.with(this.context) // Kontext der ImageView.
        .load(url) // Lädt das Bild von der angegebenen URL.
        .placeholder(null) // Setzt kein Platzhalterbild während das Bild geladen wird.
        .into(this) // Ziel-ImageView, in das das Bild geladen wird.
}
