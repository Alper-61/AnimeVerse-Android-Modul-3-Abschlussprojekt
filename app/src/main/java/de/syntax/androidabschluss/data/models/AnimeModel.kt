package de.syntax.androidabschluss

import com.squareup.moshi.Json
import java.io.Serializable

data class AnimeModel(
    @Json(name = "pagination") val pagination: AnimePagination?,
    @Json(name = "data") val data: List<AnimeData>?
)

data class AnimePagination(@Json(name = "last_visible_page") var totalPage: Int)

data class AnimeData(
    @Json(name = "mal_id") val mal_id: Int?,
    @Json(name = "url") val url: String?,
    @Json(name = "images") val images: AnimeImages?,
    @Json(name = "title") val title: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "source") val source: String?,
    @Json(name = "episodes") val episodes: Int?,
    @Json(name = "status") val status: String?,
    @Json(name = "aired") val aired: AnimeDate?,
    @Json(name = "duration") val duration: String?,
    @Json(name = "rating") val rating: String?,
    @Json(name = "score") val score: Double?,
    @Json(name = "scored_by") val scored_by: Int?,
    @Json(name = "rank") val rank: Int?,
    @Json(name = "popularity") val popularity: Int?,
    @Json(name = "synopsis") val description: String?,
    @Json(name = "background") val history: String?,
    @Json(name = "season") val season: String?,
    @Json(name = "year") val year: Int?
):Serializable

data class AnimeImages(@Json(name = "jpg") val jpg: AnimeImagesJpg?)

data class AnimeImagesJpg(
    @Json(name = "image_url") val image_url: String?,
    @Json(name = "small_image_url") val small_image_url: String?,
    @Json(name = "large_image_url") val large_image_url: String?
)

data class AnimeDate(@Json(name = "string") val date: String?)

