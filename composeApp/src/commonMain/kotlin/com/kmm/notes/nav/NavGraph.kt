package com.kmm.notes.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kmm.notes.edit.EditNoteScreen
import com.kmm.notes.list.NotesListScreen

@Composable
fun SetupNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.NotesList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.NotesList.route) {
            NotesListScreen(
                modifier,
                editNote = { noteId ->
                    navController.navigate(Screen.EditNote.withArgs(noteId))
                }
            )
        }
        composable(
            route = Screen.EditNote.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { entry ->
            EditNoteScreen(
                noteId = entry.arguments?.getLong("noteId") ?: -1L,
                modifier = modifier,
                navigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}
