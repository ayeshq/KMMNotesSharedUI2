package com.kmm.notes.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                    //TODO: pass the noteId !
                    navController.navigate(Screen.EditNote.route)
                }
            )
        }
        composable(route = Screen.EditNote.route) {
            EditNoteScreen(
                modifier,
                navigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}
