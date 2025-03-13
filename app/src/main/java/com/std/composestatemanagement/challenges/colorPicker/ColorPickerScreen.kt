package com.std.composestatemanagement.challenges.colorPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun HomeWallScreen(modifier: Modifier = Modifier) {

    var colorState by remember { mutableStateOf(Color.Red) }
    var display by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorState),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { display = !display }) {
            Text("Choose Color")
        }
        if (display) {
            DisplayColorPickerPalette { color ->
                colorState = color
            }
        }
    }
}


@Composable
fun DisplayColorPickerPalette(onColorChange: (Color) -> Unit) {
    // local state
    var selected by remember { mutableStateOf("") }

    Row {
        colors.forEach { color ->
            FilterChip(
                selected = selected == color.key,
                onClick = {
                    selected = color.key
                    onColorChange(color.value)
                },
                label = { Text(color.key) }
            )
        }
    }
}


@Preview
@Composable
private fun HomeWallPreview() {
    HomeWallScreen()
}

val colors: Map<String, Color> = mapOf(
    "Blue" to Color.Blue,
    "Green" to Color.Green,
    "Yellow" to Color.Yellow,
    "Magenta" to Color.Magenta,
    "Black" to Color.Black
)