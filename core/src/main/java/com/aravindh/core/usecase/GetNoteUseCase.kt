package com.aravindh.core.usecase

import com.aravindh.core.repository.NoteRepository

class GetNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Long) = noteRepository.get(id)

    suspend operator fun invoke() = noteRepository.getAllNotes()
}