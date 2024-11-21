package com.satriopndt.expertsystem.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        colors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White),
        leadingIcon = {
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search"
        )
    }, placeholder = { Text(text = "Search") },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(query = "", onQueryChange = {})
}