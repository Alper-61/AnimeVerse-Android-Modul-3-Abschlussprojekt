package de.syntax.androidabschluss.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query



@Dao
interface Dao  {
    @Insert
    suspend fun insertAnime(entity: EntityAnime): Long?

    @Query("SELECT * From favoriteanime")
    suspend fun getAllAnime(): List<EntityAnime>

    @Query("SELECT * FROM favoriteanime WHERE mal_id = :mal_id")
    suspend fun getAnimeById(mal_id: Int): EntityAnime?

    @Delete
    suspend fun deleteAnime(entity: EntityAnime)
}