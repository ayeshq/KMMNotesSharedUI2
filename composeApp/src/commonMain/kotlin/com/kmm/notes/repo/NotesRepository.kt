package com.kmm.notes.repo

import com.kmm.notes.entity.Note

interface NotesRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun addNote(note: Note)

    suspend fun getNoteById(id: Long): Note?

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}
