package com.kmm.notes.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmm.notes.entity.Note
import com.kmm.notes.repo.NotesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _state = mutableStateOf(EditNoteScreenState())
    val state: State<EditNoteScreenState> = _state

    private var noteId = -1L

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = _state.value.copy(isError = true)
    }

    fun loadNoteById(noteId: Long) {
        this.noteId = noteId
        viewModelScope.launch(errorHandler) {
            val note = notesRepository.getNoteById(noteId)
            _state.value = _state.value.copy(note = note)
        }
    }

    fun saveNote(title: String, content: String) {
        viewModelScope.launch(errorHandler) {
            if (noteId >= 0) {
                notesRepository.updateNote(
                    id = noteId,
                    title = title,
                    content = content
                )
            } else {
                noteId = notesRepository.addNote(title, content)
            }

            _state.value = _state.value.copy(
                isError = false,
                note = Note(
                    id = noteId,
                    title = title,
                    content = content
                )
            )
        }
    }
}

data class EditNoteScreenState(
    val note: Note? = null,
    val isError: Boolean = false
)
