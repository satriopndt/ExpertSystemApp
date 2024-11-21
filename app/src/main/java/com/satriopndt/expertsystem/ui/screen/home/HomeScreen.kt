package com.satriopndt.expertsystem.ui.screen.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satriopndt.expertsystem.ViewModelFactory
import com.satriopndt.expertsystem.di.Injection
import com.satriopndt.expertsystem.ui.component.PostingItem
import com.satriopndt.expertsystem.ui.component.SearchBar
import com.satriopndt.expertsystem.ui.theme.BiruDongker
import com.satriopndt.expertsystem.ui.theme.BluePekat

@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))

    )
) {

    val groupResearch by viewModel.groupPosting.collectAsState()
    val query by viewModel.query

    Column(
        modifier = modifier
            .fillMaxSize()
            .background
                (
                brush = Brush.verticalGradient(
                    colors = listOf(BluePekat, BiruDongker),
                    startY = 70f
                )
            )
    ) {
        Row(modifier = modifier) {
            SearchBar(query = query, onQueryChange = { viewModel::search })
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupResearch.forEach { _, posting ->
                items(posting) {
                    PostingItem(
                        username = it.name,
                        image = it.image,
                        category = it.category,
                        description = it.description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}