package com.aravindh.cleancode.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id=:id")
    fun getNote(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntity>

    @Delete
    fun delete(noteEntity: NoteEntity)
}