package com.kmm.notes.edit

import androidx.lifecycle.ViewModel
import com.kmm.notes.repo.NotesRepository

class EditNoteViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    init {
        println("Notes Repository Ref: ${notesRepository.hashCode()}")
    }
}
