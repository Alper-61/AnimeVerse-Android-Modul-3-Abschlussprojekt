package de.syntax.androidabschluss

import com.squareup.moshi.Json
import java.io.Serializable

data class HomeTopReviewsModel(
    @Json(name = "pagination") val pagination: TopReviewsPagination?,
    @Json(name = "data") val data: List<TopReviewsData>?
)

data class TopReviewsPagination(@Json(name = "last_visible_page") var totalPage: Int)

data class TopReviewsData(
    @Json(name = "mal_id") val mal_id: Int?,
    @Json(name = "url") val url: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "review") val review: String?,
    @Json(name = "score") val score: Int?,
    @Json(name = "entry") val entry: TopReviewsEntry?
):Serializable

data class TopReviewsEntry(@Json(name = "title") val title:String,@Json(name = "images") val images: TopReviewsImagesJpg)

data class TopReviewsImagesJpg(
    @Json(name = "jpg") val jpg: TopReviewsImageURL
)

data class TopReviewsImageURL(@Json(name = "image_url") val image_url: String)
