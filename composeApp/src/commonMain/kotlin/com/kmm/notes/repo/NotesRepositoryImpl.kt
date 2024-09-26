package com.kmm.notes.repo

import com.kmm.notes.db.DatabaseDriverFactory
import com.kmm.notes.db.NotesDatabase
import com.kmm.notes.entity.Note

class NotesRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : NotesRepository {

    private val database = NotesDatabase(databaseDriverFactory.createDriver())

    private val dbQuery = database.notesDatabaseQueries

    override suspend fun getAllNotes(): List<Note> =
        dbQuery.selectAllNotes(::mapNote).executeAsList()

    override suspend fun addNote(note: Note) {
        dbQuery.insertNote(
            title = note.title,
            content = note.content
        )
    }

    override suspend fun getNoteById(id: Long): Note? =
        dbQuery
            .selectNoteById(
                id,
                ::mapNote
            ).executeAsOneOrNull()

    override suspend fun updateNote(note: Note) {
        dbQuery
            .updateNotByid(
                id = note.id,
                title = note.title,
                content = note.content
            )
    }

    override suspend fun deleteNote(note: Note) {
        dbQuery.deleteNoteById(note.id)
    }

    private fun mapNote(
        id: Long,
        title: String,
        content: String
    ): Note {
        return Note(
            id = id,
            title = title,
            content = content
        )
    }
}
