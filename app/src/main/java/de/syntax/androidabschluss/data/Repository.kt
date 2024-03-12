package de.syntax.androidabschluss.data

import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.remote.AnimeApiService

class Repository ( private val recipeApiService: AnimeApiService,
private val favoriteAnime: AppDatabase, private val favoriteCharacters: AppDatabaseCharacters
) {

}