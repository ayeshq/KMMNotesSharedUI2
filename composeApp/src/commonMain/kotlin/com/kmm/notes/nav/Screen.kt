package com.kmm.notes.nav

sealed class Screen(val route: String) {
    data object NotesList: Screen(route = "notesList")
    data object EditNote: Screen(route = "editDetail")
}
