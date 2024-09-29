package com.kmm.notes.repo

import com.kmm.notes.db.DatabaseDriverFactory
import com.kmm.notes.db.NotesDatabase
import com.kmm.notes.entity.Note
import kotlinx.coroutines.flow.MutableStateFlow

class NotesRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : NotesRepository {

    private val database = NotesDatabase(databaseDriverFactory.createDriver())

    private val dbQuery = database.notesDatabaseQueries

    override val notesState = MutableStateFlow<List<Note>>(mutableListOf())

    init {
        loadAlNotes()
    }

    override suspend fun getAllNotes(): List<Note> = notesState.value

    override suspend fun addNote(
        title: String,
        content: String
    ): Long {
        dbQuery.insertNote(title, content)
        loadAlNotes()
        return latestNoteId()
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
        loadAlNotes()
    }

    override suspend fun deleteNote(note: Note) {
        dbQuery.deleteNoteById(note.id)
        loadAlNotes()
    }

    private fun latestNoteId(): Long = dbQuery.lastestNoteId().executeAsOne()

    private fun loadAlNotes() {
        val notes = dbQuery.selectAllNotes(::mapNote).executeAsList()
        notesState.value = notes
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
