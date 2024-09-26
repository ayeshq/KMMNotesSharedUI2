package com.kmm.notes.nav

sealed class Screen(val route: String) {
    data object NotesList: Screen(route = "notesList")
    data object EditNote: Screen(route = "editDetail")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
