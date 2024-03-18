package de.syntax.androidabschluss.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntax.androidabschluss.AnimeDetailModel
import de.syntax.androidabschluss.AnimeModel
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.CharactersModel
import de.syntax.androidabschluss.HomeTopReviewsModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.jikan.moe/v4/"

private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val httpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .build()

interface AnimeApiService {

    @GET("anime")
    suspend fun animeList(@Query("page") page: String): AnimeModel

    @GET("characters")
    suspend fun charactersList(@Query("page") page: String): CharactersModel

    @GET("anime/{mal_id}/full")
    suspend fun animeDetails(@Path("mal_id") mal_id: String): AnimeDetailModel

    @GET("characters/{mal_id}/full")
    suspend fun charactersDetails(@Path("mal_id") mal_id: String): CharacterDetailModel

    @GET("top/reviews")
    suspend fun topReviewsList(): HomeTopReviewsModel

    @GET("top/anime")
    suspend fun topAnimeList(@Query("page") page: String): AnimeModel

    @GET("top/characters")
    suspend fun topCharactersList(@Query("page") page: String): CharactersModel
}

object AnimeApi {
    val retrofitService: AnimeApiService by lazy { retrofit.create(AnimeApiService::class.java)}
}
