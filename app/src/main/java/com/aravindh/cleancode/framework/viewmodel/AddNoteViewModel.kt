package com.aravindh.cleancode.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.cleancode.util.Utils
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

    private var currentNote = Note(title = "", content = "", creationTime = 0L, updateTime = 0L)

    private var _isSaved = MutableLiveData<STATUS>()
    val isSaved: LiveData<STATUS> = _isSaved

    private var _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    var title = MutableLiveData("")
    var content = MutableLiveData("")


    fun saveNote() {
        val title = title.value.toString()
        val content = content.value.toString()
        val time = System.currentTimeMillis()
        var creationTime = time

        if (title.trim().equals("", ignoreCase = true)) {
            _errorMessage.value = "Title should not be empty!"
            return
        }

        if (content.trim().equals("", ignoreCase = true)) {
            _errorMessage.value = "Content should not be empty!"
            return
        }

        if (currentNote.id != 0L) {
            creationTime = currentNote.creationTime
        }

        currentNote =
            Note(
                title = title,
                content = content,
                creationTime = creationTime,
                updateTime = time,
                id = currentNote.id
            )


        coroutineScope.launch {
            useCases.addNoteUseCase(currentNote)

            if (currentNote.id == 0L) {
                _isSaved.postValue(STATUS.ADD)
            } else {
                _isSaved.postValue(STATUS.UPDATE)
            }

        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNoteUseCase.invoke(id)
            if (note != null) {
                currentNote = note
                title.postValue(note.title)
                content.postValue(note.content)
            }
        }
    }

    fun deleteNote() {
        coroutineScope.launch {
            if (currentNote.id != 0L) {
                useCases.deleteNoteUseCase.invoke(currentNote)
                _isSaved.postValue(STATUS.DELETE)
            }
        }
    }

    enum class STATUS {
        ADD,
        UPDATE,
        DELETE,
    }
}