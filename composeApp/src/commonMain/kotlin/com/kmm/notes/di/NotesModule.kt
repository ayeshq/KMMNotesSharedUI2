package com.kmm.notes.di

import com.kmm.notes.edit.EditNoteViewModel
import com.kmm.notes.list.NotesListViewModel
import com.kmm.notes.repo.NotesRepository
import com.kmm.notes.repo.NotesRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    viewModel { NotesListViewModel(get()) }
    viewModel { EditNoteViewModel(get()) }
}

