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

    override suspend fun addNote(
        title: String,
        content: String
    ) {
        dbQuery.insertNote(title, content)
    }

    override suspend fun getNoteById(id: Long): Note? =
        dbQuery
            .selectNoteById(
                id,
                ::mapNote
            ).executeAsOneOrNull()

    override suspend fun updateNote(
        id: Long,
        title: String,
        content: String
    ) {
        dbQuery
            .updateNoteByid(
                id = id,
                title = title,
                content = content
            )
    }

    override suspend fun deleteNote(note: Note) {
        dbQuery.deleteNoteById(note.id)
    }

    override suspend fun latestNoteId(): Long = dbQuery.lastestNoteId().executeAsOne()

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
