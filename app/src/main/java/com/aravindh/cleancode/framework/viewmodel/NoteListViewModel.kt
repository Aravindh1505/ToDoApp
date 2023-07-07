package com.aravindh.cleancode.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val useCases: UseCases,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private var _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNotes() {
        coroutineScope.launch {
            val list = useCases.getNoteUseCase.invoke()
            _notes.postValue(list)
        }
    }
}