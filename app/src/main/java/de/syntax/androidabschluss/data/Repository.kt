package de.syntax.androidabschluss.data


import de.syntax.androidabschluss.AnimeModel
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.remote.AnimeApiService
import retrofit2.Call

class Repository(private val animeApiService: AnimeApiService,private val appDatabase: AppDatabase,
    private val appDatabaseCharacters: AppDatabaseCharacters){


suspend fun getAnimeList(page:Int): AnimeModel?{
    return try {
        animeApiService.animeList(page.toString())
    } catch (e:Exception){
        null
    }
}
}