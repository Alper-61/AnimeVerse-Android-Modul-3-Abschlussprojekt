package de.syntax.androidabschluss.data


import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.data.models.AnimeDetailModel
import de.syntax.androidabschluss.data.models.AnimeModel
import de.syntax.androidabschluss.data.models.CharactersModel
import de.syntax.androidabschluss.data.models.HomeTopReviewsModel
import de.syntax.androidabschluss.data.remote.AnimeApiService

// Repository-Klasse, die als Datenzugriffsschicht für die Anwendung dient.
class Repository(
    private val animeApiService: AnimeApiService, // API Service für den Zugriff auf Anime-Daten.
    private val appDatabase: AppDatabase, // Datenbankinstanz für Anime.
    private val appDatabaseCharacters: AppDatabaseCharacters // Datenbankinstanz für Charaktere.
) {

    // Holt eine Liste von Animes von der API.
    suspend fun getAnimeList(page: Int): AnimeModel? {
        return try {
            animeApiService.animeList(page.toString())
        } catch (e: Exception) {
            null // Bei einem Fehler wird null zurückgegeben.
        }
    }

    // Holt eine Liste von Charakteren von der API.
    suspend fun getCharactersList(page: Int): CharactersModel? {
        return try {
            animeApiService.charactersList(page.toString())
        } catch (e: Exception) {
            null
        }
    }

    // Holt Detailinformationen zu einem spezifischen Anime von der API.
    suspend fun getAnimeDetail(mal_id: Int): AnimeDetailModel? {
        return try {
            animeApiService.animeDetails(mal_id.toString())
        } catch (e: Exception) {
            null
        }
    }

    // Holt Detailinformationen zu einem spezifischen Charakter von der API.
    suspend fun getCharacterDetails(mal_id: Int): CharacterDetailModel? {
        return try {
            animeApiService.charactersDetails(mal_id.toString())
        } catch (e: Exception) {
            null
        }
    }

    // Holt eine Liste der top-bewerteten Rezensionen von der API.
    suspend fun getTopReviewsList(): HomeTopReviewsModel? {
        return try {
            animeApiService.topReviewsList()
        } catch (e: Exception) {
            null
        }
    }

    // Holt eine Liste der top-bewerteten Animes von der API.
    suspend fun getTopAnimeList(page: Int): AnimeModel? {
        return try {
            animeApiService.topAnimeList(page.toString())
        } catch (e: Exception) {
            null
        }
    }

    // Holt eine Liste der top-bewerteten Charaktere von der API.
    suspend fun getTopCharactersList(page: Int): CharactersModel? {
        return try {
            animeApiService.topCharactersList(page.toString())
        } catch (e: Exception) {
            null
        }
    }

    // Methoden zur Verwaltung von favorisierten Animes in der lokalen Datenbank.
    suspend fun getFavoriteAnime(): List<EntityAnime> {
        return appDatabase.favoriteDao().getAllAnime()
    }

    // Fügt einen Anime zu den Favoriten hinzu, wenn er noch nicht existiert.
    suspend fun addFavoriteAnime(entity: EntityAnime): Long? {
        val search = appDatabase.favoriteDao().getAnimeById(entity.mal_id)
        if (search == null) {
            return appDatabase.favoriteDao().insertAnime(entity)
        } else {
            return -1L // Anime bereits vorhanden.
        }
    }

    // Entfernt einen Anime aus den Favoriten.
    suspend fun deleteFavoriteAnime(entity: EntityAnime) {
        appDatabase.favoriteDao().deleteAnime(entity)
    }

    // Sucht nach einem favorisierten Anime anhand seiner ID.
    suspend fun searchFavoriteAnime(mal_id: Int): EntityAnime? {
        return appDatabase.favoriteDao().getAnimeById(mal_id)
    }

    // Methoden zur Verwaltung von favorisierten Charakteren in der lokalen Datenbank.
    suspend fun getFavoriteCharacters(): List<EntityCharacters> {
        return appDatabaseCharacters.favoriteDao().getAllCharacters()
    }

    // Fügt einen Charakter zu den Favoriten hinzu, wenn er noch nicht existiert.
    suspend fun addFavoriteCharacter(entity: EntityCharacters): Long? {
        return if (appDatabaseCharacters.favoriteDao().getCharactersById(entity.mal_id) == null) {
            appDatabaseCharacters.favoriteDao().insertCharacters(entity)
        } else {
            -1L // Charakter bereits vorhanden.
        }
    }

    // Entfernt einen Charakter aus den Favoriten.
    suspend fun deleteFavoriteCharacter(entity: EntityCharacters) {
        appDatabaseCharacters.favoriteDao().deleteCharacters(entity)
    }

    // Sucht nach einem favorisierten Charakter anhand seiner ID.
    suspend fun searchFavoriteCharacter(mal_id: Int): EntityCharacters? {
        return appDatabaseCharacters.favoriteDao().getCharactersById(mal_id)
    }
}





