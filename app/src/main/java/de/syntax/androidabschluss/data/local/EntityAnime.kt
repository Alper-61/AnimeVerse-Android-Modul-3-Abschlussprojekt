package de.syntax.androidabschluss.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favoriteanime")
data class EntityAnime(
    @PrimaryKey
    @ColumnInfo(name = "mal_id") @NotNull val mal_id: Int,
    @ColumnInfo(name = "image_url") @NotNull val image_url: String,
    @ColumnInfo(name = "title") @NotNull val title: String,
    @ColumnInfo(name = "type") @NotNull val type: String,
    @ColumnInfo(name = "source") @NotNull val source: String
)

data class TempEntityAnimeModel(
    var mal_id: Int,
    var image_url: String,
    var title: String,
    var type: String,
    var source: String
)