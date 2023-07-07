package com.aravindh.cleancode.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val useCases: UseCases,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

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