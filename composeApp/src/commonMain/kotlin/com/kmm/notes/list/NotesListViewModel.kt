package com.kmm.notes.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmm.notes.entity.Note
import com.kmm.notes.repo.NotesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = _state.value.copy(isError = true)
    }

    private val _state = mutableStateOf(NotesListScreenState())
    val state: State<NotesListScreenState> = _state

    init {
        loadAllNotes()
    }

    private fun loadAllNotes() {
        viewModelScope.launch(errorHandler) {
            val notes = notesRepository.getAllNotes()
            _state.value = _state.value.copy(
                isEmpty = notes.isEmpty(),
                isError = false,
                notes = notes
            )
        }
    }
}

data class NotesListScreenState(
    val isEmpty: Boolean = false,
    val isError: Boolean = false,
    val notes: List<Note> = emptyList()
)
