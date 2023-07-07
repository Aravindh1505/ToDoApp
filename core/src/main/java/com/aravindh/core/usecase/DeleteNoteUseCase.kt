package com.aravindh.core.usecase

import com.aravindh.core.data.Note
import com.aravindh.core.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.remove(note)

}