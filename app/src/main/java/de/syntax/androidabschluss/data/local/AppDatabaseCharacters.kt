package de.syntax.androidabschluss.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityCharacters::class], version = 1)
abstract class AppDatabaseCharacters : RoomDatabase() {
    abstract fun favoriteDao(): DaoCharacters

    companion object {
        private var INSTANCE: AppDatabaseCharacters? = null
        fun getInstance(context: Context): AppDatabaseCharacters {
            if (INSTANCE == null) {
                synchronized(AppDatabaseCharacters::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabaseCharacters::class.java, "appdatabasecha.sqlite")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}