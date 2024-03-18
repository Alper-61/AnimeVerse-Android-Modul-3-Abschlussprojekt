package de.syntax.androidabschluss.data


import androidx.lifecycle.LiveData
import de.syntax.androidabschluss.AnimeDetailModel
import de.syntax.androidabschluss.AnimeModel
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.CharactersModel
import de.syntax.androidabschluss.HomeTopReviewsModel
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.remote.AnimeApiService
import retrofit2.Call

class Repository(private val animeApiService: AnimeApiService,private val appDatabase: AppDatabase,
    private val appDatabaseCharacters: AppDatabaseCharacters){

    val favAnime = appDatabase.favoriteDao().getAnimeById()


suspend fun getAnimeList(page:Int): AnimeModel?{
    return try {
        animeApiService.animeList(page.toString())
    } catch (e:Exception){
        null
    }
}

    suspend fun getCharactersList(page:Int): CharactersModel?{
        return try {
            animeApiService.charactersList(page.toString())
        } catch (e:Exception) {
            null
        }
    }

suspend fun getAnimeDetail(mal_id:Int): AnimeDetailModel?{
    return try {
        animeApiService.animeDetails(mal_id.toString())
    }catch (e:Exception){
        null
    }
}

    suspend fun getCharacterDetails(mal_id: Int): CharacterDetailModel?{
        return try {
            animeApiService.charactersDetails(mal_id.toString())
        } catch (e:Exception){
            null
        }
    }

    suspend fun getTopReviewsList(): HomeTopReviewsModel?{
        return try {
            animeApiService.topReviewsList()
        } catch (e:Exception){
            null
        }
    }

    suspend fun getTopAnimeList(page: Int): AnimeModel?{
        return try {
            animeApiService.topAnimeList(page.toString())
        } catch (e:Exception){
            null
        }
    }

    suspend fun getTopCharactersList(page: Int):CharactersModel?{
        return try {
            animeApiService.topCharactersList(page.toString())
        }catch (e:Exception){
            null
        }
    }




}