package de.syntax.androidabschluss

import com.squareup.moshi.Json
import java.io.Serializable

data class CharactersModel(
    @Json(name = "pagination") val pagination: CharactersPagination?,
    @Json(name = "data") val data: List<CharactersData>?
)

data class CharactersPagination(@Json(name = "last_visible_page") var totalPage: Int)

data class CharactersData(
    @Json(name = "mal_id") val mal_id: Int?,
    @Json(name = "url") val url: String?,
    @Json(name = "images") val images: CharactersImages?,
    @Json(name = "name") val name: String?,
    @Json(name = "about") val about: String?,
):Serializable

data class CharactersImages(@Json(name = "jpg") val jpg: CharactersImagesJpg)

data class CharactersImagesJpg(
    @Json(name = "image_url") val image_url: String
)

