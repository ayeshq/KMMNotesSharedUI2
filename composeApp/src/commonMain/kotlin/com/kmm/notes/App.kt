package com.kmm.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kmm.notes.nav.SetupNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        //TODO: Declare KoinContext here!
        val navController = rememberNavController()
        SetupNavGraph(
            modifier = Modifier.fillMaxSize(),
            navController = navController
        )
    }
}
