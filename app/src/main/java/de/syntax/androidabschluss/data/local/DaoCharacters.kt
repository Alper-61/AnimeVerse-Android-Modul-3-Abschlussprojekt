package de.syntax.androidabschluss.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query



@Dao
interface DaoCharacters  {
    @Insert
    suspend fun insertCharacters(entity: EntityCharacters): Long?

    @Query("SELECT * From favoritecharacters")
    suspend fun getAllCharacters(): List<EntityCharacters>

    @Query("SELECT * FROM favoritecharacters WHERE mal_id = :mal_id")
    suspend fun getCharactersById(mal_id: Int): EntityCharacters?

    @Delete
    suspend fun deleteCharacters(entity: EntityCharacters)
}