package de.syntax.androidabschluss

import com.squareup.moshi.Json

data class CharacterDetailModel(@Json(name = "data") val data: CharactersData)
