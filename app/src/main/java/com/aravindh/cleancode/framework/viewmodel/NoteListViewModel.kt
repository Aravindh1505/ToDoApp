package com.aravindh.cleancode.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aravindh.cleancode.framework.RoomNoteDataSource
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.core.data.Note
import com.aravindh.core.repository.NoteRepository
import com.aravindh.core.usecase.AddNoteUseCase
import com.aravindh.core.usecase.DeleteNoteUseCase
import com.aravindh.core.usecase.GetNoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val noteRepository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        addNoteUseCase = AddNoteUseCase(noteRepository),
        getNoteUseCase = GetNoteUseCase(noteRepository),
        deleteNoteUseCase = DeleteNoteUseCase(noteRepository)
    )

    private var _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNotes() {
        coroutineScope.launch {
            val list = useCases.getNoteUseCase.invoke()
            _notes.postValue(list)
        }
    }
}