package com.std.composestatemanagement.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RatingSystem() {
    // hoisted state: rating: Int
    var rating by remember { mutableIntStateOf(0) }

    Text("Selected rating: ${rating + 1}")
    DisplayRatingBar(
        noStars = 5,
        rating = rating,
        onRatingChange = { selectedRating -> rating = selectedRating })
}

@Composable
fun DisplayRatingBar(noStars: Int, rating: Int, onRatingChange: (Int) -> Unit) {
    Row {
        repeat(noStars) { index ->
            IconButton(onClick = { onRatingChange(index) }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = if (index <= rating) Color.Yellow else Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRatingSystem() {
    RatingSystem()
}