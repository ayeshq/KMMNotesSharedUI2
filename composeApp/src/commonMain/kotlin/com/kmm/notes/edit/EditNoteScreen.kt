package com.kmm.notes.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditNoteScreen(
    noteId: Long,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    val viewModel = koinViewModel<EditNoteViewModel>()
    val state by remember { viewModel.state }

    val title = remember { mutableStateOf(state.note?.title ?: "") }
    val content = remember { mutableStateOf(state.note?.content ?: "") }

    if (noteId >= 0L) {
        viewModel.loadNoteById(noteId)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
                navigationIcon = {
                    Button(onClick = {
                        navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveNote(title.value, content.value)
            }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Add Note")
            }
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
        ) {
            //Title TextField
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Title")},
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                maxLines = 2,
                minLines = 1,
            )
            Spacer(modifier = Modifier.size(16.dp))
            //Content TextField
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Content")},
                value = content.value,
                onValueChange = { content.value = it },
            )
        }
    }
}
