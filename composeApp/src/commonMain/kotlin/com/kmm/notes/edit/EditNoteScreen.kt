package com.kmm.notes.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditNoteScreen(
    noteId: Long,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    val viewModel = koinViewModel<EditNoteViewModel>()
    println("NoteId = $noteId")

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
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                navigateUp()
            }) {
                Text(text = "Save Note")
            }
        }
    }
}
