package de.syntax.androidabschluss.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EntityAnime::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): Dao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "appdatabase.sqlite")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}