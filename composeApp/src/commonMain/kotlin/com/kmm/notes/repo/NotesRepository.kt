package com.kmm.notes.repo

import com.kmm.notes.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    val notesState: Flow<List<Note>>

    suspend fun getAllNotes(): List<Note>

    suspend fun addNote(
        title: String,
        content: String
    ): Long

    suspend fun getNoteById(id: Long): Note?

    suspend fun updateNote(
        id: Long,
        title: String,
        content: String
    )

    suspend fun deleteNote(note: Note)
}
