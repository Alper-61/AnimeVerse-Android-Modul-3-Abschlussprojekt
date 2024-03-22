package de.syntax.androidabschluss.data.models

import com.squareup.moshi.Json
import de.syntax.androidabschluss.data.models.AnimeData

data class AnimeDetailModel(@Json(name = "data") val data: AnimeData)
