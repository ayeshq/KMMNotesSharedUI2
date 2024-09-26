package com.kmm.notes.list

import androidx.lifecycle.ViewModel
import com.kmm.notes.repo.NotesRepository

class NotesListViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    init {
        println("Notes Repository Ref: ${notesRepository.hashCode()}")
    }
}
