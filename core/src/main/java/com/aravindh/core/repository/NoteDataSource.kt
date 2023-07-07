package com.aravindh.core.repository

import com.aravindh.core.data.Note

interface NoteDataSource {
    suspend fun addNote(note: Note)
    suspend fun get(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun remove(note: Note)
}