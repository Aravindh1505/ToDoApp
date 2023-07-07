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

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val noteRepository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        addNoteUseCase = AddNoteUseCase(noteRepository),
        getNoteUseCase = GetNoteUseCase(noteRepository),
        deleteNoteUseCase = DeleteNoteUseCase(noteRepository)
    )

    private var _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean> = _isSaved

    private var _note = MutableLiveData<Note?>()
    val note: LiveData<Note?> = _note


    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNoteUseCase(note)
            _isSaved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNoteUseCase.invoke(id)
            _note.postValue(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.deleteNoteUseCase.invoke(note)
            _isSaved.postValue(true)
        }
    }
}