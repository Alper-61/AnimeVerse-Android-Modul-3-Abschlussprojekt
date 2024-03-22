package de.syntax.androidabschluss

import com.squareup.moshi.Json
import de.syntax.androidabschluss.data.models.CharactersData

data class CharacterDetailModel(@Json(name = "data") val data: CharactersData)
