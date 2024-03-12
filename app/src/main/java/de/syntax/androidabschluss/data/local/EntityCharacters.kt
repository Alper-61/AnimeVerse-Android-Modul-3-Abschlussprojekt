package de.syntax.androidabschluss.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favoritecharacters")
data class EntityCharacters(
    @PrimaryKey
    @ColumnInfo(name = "mal_id") @NotNull val mal_id: Int,
    @ColumnInfo(name = "image_url") @NotNull val image_url: String,
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "about") @NotNull val about: String
)

data class TempEntityCharacters(
    var mal_id: Int, var image_url: String, var name: String, var about: String
)
