package com.satriopndt.expertsystem.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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

    Row (modifier = modifier.padding(8.dp).clip(RoundedCornerShape(20.dp))){

        TextField(
            value = query,
            onValueChange = onQueryChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            ),
            leadingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            placeholder = { Text(text = "Search") },
            modifier = modifier
                .height(50.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
        )
    }


}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(query = "", onQueryChange = {})
}