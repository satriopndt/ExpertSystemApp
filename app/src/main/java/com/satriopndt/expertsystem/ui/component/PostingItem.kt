package com.satriopndt.expertsystem.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satriopndt.expertsystem.R

@Composable
fun PostingItem(
    username: String,
    image: Int,
    category: String,
    description: String,
    modifier: Modifier = Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .shadow(elevation = 30.dp, shape = RoundedCornerShape(12.dp), clip = true),
    ) {

        Column(modifier = modifier.padding(8.dp)) {
            Text(text = username, maxLines = 1, style = MaterialTheme.typography.titleSmall)

            //Spacer(modifier = modifier.padding(8.dp))

            Image(
                painter = painterResource(image), contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(500.dp)

            )
            Row(
                modifier = modifier
                    .height(28.dp)
                    .padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                    contentDescription = null
                )
                Spacer(modifier = modifier.padding(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    contentDescription = null
                )
            }
            Text(text = category, maxLines = 1, style = MaterialTheme.typography.bodySmall)
            Text(text = description, maxLines = 4, style = MaterialTheme.typography.bodySmall)
        }



    }


}

@Preview(showBackground = true)
@Composable
fun PostingItemPreview() {
    PostingItem("RayiMeng", R.drawable.kebun_teh, "Seminar", "Lorem Ipsumwekfdslwknfvv")
}