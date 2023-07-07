package com.aravindh.cleancode.framework

import android.content.Context
import com.aravindh.cleancode.framework.db.NoteDao
import com.aravindh.cleancode.framework.db.NoteDatabase
import com.aravindh.cleancode.framework.db.NoteEntity
import com.aravindh.core.data.Note
import com.aravindh.core.repository.NoteDataSource
import javax.inject.Inject

class RoomNoteDataSource @Inject constructor(
    private val noteDao: NoteDao
) : NoteDataSource {

    override suspend fun addNote(note: Note) = noteDao.addNote(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNote(id)?.toNote()

    override suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.delete(NoteEntity.fromNote(note))
}