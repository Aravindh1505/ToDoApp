package com.aravindh.core.repository

import com.aravindh.core.data.Note

class NoteRepository(private val noteDataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = noteDataSource.addNote(note)
    suspend fun get(id: Long): Note? = noteDataSource.get(id)
    suspend fun getAllNotes(): List<Note> = noteDataSource.getAllNotes()
    suspend fun remove(note: Note) = noteDataSource.remove(note)
}