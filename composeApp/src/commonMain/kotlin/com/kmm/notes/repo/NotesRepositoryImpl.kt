package com.kmm.notes.repo

import com.kmm.notes.db.DatabaseDriverFactory

class NotesRepositoryImpl(
    private val databaseDriverFactory: DatabaseDriverFactory
) : NotesRepository {

    init {
        println("databaseDriverFactory ref: ${databaseDriverFactory.hashCode()}")
    }
}
