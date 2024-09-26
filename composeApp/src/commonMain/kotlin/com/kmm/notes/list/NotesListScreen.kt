package com.kmm.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kmm.notes.entity.Note
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    editNote: (Long) -> Unit
) {
    val viewModel = koinViewModel<NotesListViewModel>()
    val state by remember { viewModel.state }

    val allNotes = remember { mutableStateOf(state.notes) }

    //TODO find a way to update the list when a new note is added/updated adn the screen is navigated back to the list!
    LaunchedEffect(state.notes) {
        allNotes.value = state.notes
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "KMM Notes",
                        style = MaterialTheme.typography.h5
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //Navigate to the edit screen, with -1L as the note ID to indicate a new note
                editNote(-1L)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (state.isEmpty) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "You don't have any notes yet! You can add a new note by clicking on the + button",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                LazyColumn(
                    modifier = modifier.padding(vertical = 4.dp)
                ) {
                    val onNoteClicked: (Long) -> Unit = { noteId ->
                        editNote(noteId)
                    }

                    items(items = allNotes.value) {
                        NoteCard(
                            note = it,
                            modifier = modifier,
                            onNoteClicked = onNoteClicked
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Long) -> Unit
) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        onClick = { onNoteClicked(note.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h4,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                maxLines = 3
            )
        }
    }
}
