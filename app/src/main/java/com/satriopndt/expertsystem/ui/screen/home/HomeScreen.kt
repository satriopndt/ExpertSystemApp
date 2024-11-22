package com.satriopndt.expertsystem.ui.screen.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.ViewModelFactory
import com.satriopndt.expertsystem.di.Injection
import com.satriopndt.expertsystem.navigation.Screen
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

    ),
    navController: NavHostController
) {

    val groupPosting by viewModel.groupPosting.collectAsState()
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
        Row(
            modifier = modifier
                .height(IntrinsicSize.Min)
                .padding(start = 14.dp, end = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                query = query, onQueryChange =  viewModel::search,
                modifier = modifier.fillMaxHeight()
            )
            Spacer(modifier = modifier.padding(8.dp))

            IconButton(
                modifier = modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                onClick = { navController.navigate(Screen.Notification.route) },

                ) {
                Icon(
                    modifier = modifier.size(50.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.basil_notification_outline),
                    contentDescription = null
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),

            ) {
            groupPosting.forEach { _, posting ->
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
    HomeScreen(navController = rememberNavController())
}