package de.syntax.androidabschluss

import com.squareup.moshi.Json

data class AnimeDetailModel(@Json(name = "data") val data: AnimeData)
