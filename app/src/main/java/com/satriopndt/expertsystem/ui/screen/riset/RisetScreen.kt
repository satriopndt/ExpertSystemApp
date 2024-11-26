package com.satriopndt.expertsystem.ui.screen.riset

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ResearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .padding()
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Coming soon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResearchScreenPreview() {
    ResearchScreen(navController = rememberNavController())
}