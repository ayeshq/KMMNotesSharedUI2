package com.kmm.notes.di

import com.kmm.notes.db.DatabaseDriverFactory
import com.kmm.notes.db.IOSDatabaseDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

val iosModule = module {
    single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
}

fun initializeKoinForIOS() {
    startKoin {
        modules(iosModule, notesModule)
    }
}
