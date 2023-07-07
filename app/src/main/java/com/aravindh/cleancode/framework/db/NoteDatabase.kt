package com.aravindh.cleancode.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        private const val DATABASE_NAME = "note.db"

        private var instance: NoteDatabase? = null

        fun create(context: Context): NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(context: Context): NoteDatabase {
            return (instance ?: create(context).also { instance = it })
        }
    }
}