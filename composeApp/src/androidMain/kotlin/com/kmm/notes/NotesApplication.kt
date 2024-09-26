package com.kmm.notes

import android.app.Application
import com.kmm.notes.db.AndroidDatabaseDriverFactory
import com.kmm.notes.db.DatabaseDriverFactory
import com.kmm.notes.di.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val androidMode = module {
            single { androidContext() }
            single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(this@NotesApplication) }
        }

        startKoin {
            modules(
                androidMode,
                notesModule
            )
        }
    }
}
