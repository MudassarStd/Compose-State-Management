package com.std.composestatemanagement.challenges.multipleCheckboxes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PizzeraHost(modifier: Modifier = Modifier) {

    val selectedToppings = remember { mutableStateListOf<String>() }

    Column {
        Text("Select pizza toppings", fontWeight = FontWeight.Bold)
        Text("Currently selected: ${selectedToppings.joinToString(",")}")

        DisplayToppingSelection(selectedToppings, onSelected = { selectedToppings.add(it) },
            onRemoved = { selectedToppings.remove(it) })
    }
}

@Composable
fun DisplayToppingSelection(selectedToppings: List<String>, onSelected: (String) -> Unit, onRemoved: (String) -> Unit) {

    Column {
        toppings.forEach { topping ->
            val isSelected = topping in selectedToppings
            Checkbox(checked = isSelected, onCheckedChange = { checked ->
                if (checked) {
                    onSelected(topping)
                } else {
                    onRemoved(topping)
                }
            })
        }
    }
}

val toppings = listOf("Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon")

@Preview(showBackground = true)
@Composable
private fun PreviewToppingSelection() {
    PizzeraHost()
}