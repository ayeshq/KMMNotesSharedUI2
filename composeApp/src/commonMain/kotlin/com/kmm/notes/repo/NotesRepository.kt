package com.kmm.notes.repo

import com.kmm.notes.entity.Note

interface NotesRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun addNote(
        title: String,
        content: String
    )

    suspend fun getNoteById(id: Long): Note?

    suspend fun updateNote(
        id: Long,
        title: String,
        content: String
    )

    suspend fun deleteNote(note: Note)

    suspend fun latestNoteId(): Long
}
