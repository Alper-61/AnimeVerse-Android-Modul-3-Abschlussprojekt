package de.syntax.androidabschluss.data


import de.syntax.androidabschluss.data.models.AnimeDetailModel
import de.syntax.androidabschluss.data.models.AnimeModel
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.data.models.CharactersModel
import de.syntax.androidabschluss.data.models.HomeTopReviewsModel
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.local.Dao
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.data.remote.AnimeApiService

class Repository(private val animeApiService: AnimeApiService,private val appDatabase: AppDatabase,
    private val appDatabaseCharacters: AppDatabaseCharacters){




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

    suspend fun getTopCharactersList(page: Int): CharactersModel?{
        return try {
            animeApiService.topCharactersList(page.toString())
        }catch (e:Exception){
            null
        }
    }


        suspend fun getFavoriteAnime(): List<EntityAnime> {
            return appDatabase.favoriteDao().getAllAnime()
        }

        suspend fun addFavoriteAnime(entity: EntityAnime): Long? {
            val search = appDatabase.favoriteDao().getAnimeById(entity.mal_id)
            return if (search == null) {
                appDatabase.favoriteDao().insertAnime(entity)
            } else {
                -1 // Indikator, dass das Anime bereits in der Datenbank existiert
            }
        }

        suspend fun deleteFavoriteAnime(entity: EntityAnime) {
            appDatabase.favoriteDao().deleteAnime(entity)
        }

        suspend fun searchFavoriteAnime(mal_id: Int): EntityAnime? {
            return appDatabase.favoriteDao().getAnimeById(mal_id)
        }

    suspend fun getFavoriteCharacters(): List<EntityCharacters> {
        return appDatabaseCharacters.favoriteDao().getAllCharacters()
    }

    suspend fun addFavoriteCharacter(entity: EntityCharacters): Long? {
        return if (appDatabaseCharacters.favoriteDao().getCharactersById(entity.mal_id) == null) {
            appDatabaseCharacters.favoriteDao().insertCharacters(entity)
        } else {
            -1L // Indikator, dass der Charakter bereits in der Datenbank existiert
        }
    }

    suspend fun deleteFavoriteCharacter(entity: EntityCharacters) {
        appDatabaseCharacters.favoriteDao().deleteCharacters(entity)
    }

    suspend fun searchFavoriteCharacter(mal_id: Int): EntityCharacters? {
        return appDatabaseCharacters.favoriteDao().getCharactersById(mal_id)
    }



}





