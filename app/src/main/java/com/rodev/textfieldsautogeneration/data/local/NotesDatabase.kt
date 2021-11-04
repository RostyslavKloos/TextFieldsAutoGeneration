package com.rodev.textfieldsautogeneration.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodev.textfieldsautogeneration.BuildConfig
import com.rodev.textfieldsautogeneration.data.local.model.Notes

@Database(
    entities = [
        Notes::class], version = 1, exportSchema = false
)

abstract class NotesDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: NotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            BuildConfig.APPLICATION_ID
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    }

    abstract fun notesDao(): NotesDao

}