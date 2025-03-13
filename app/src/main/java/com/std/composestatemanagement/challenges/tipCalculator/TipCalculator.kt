package com.std.composestatemanagement.challenges.tipCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TipCalculator(modifier: Modifier = Modifier) {
    var bill by remember { mutableStateOf("") }
    var tipState by remember { mutableIntStateOf(0) }
    var result by remember { mutableIntStateOf(0) }

    Column (modifier =  Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        TextField(value = bill, onValueChange = { bill = it })
        Text("Select Tip(%): ", fontWeight = FontWeight.Bold)
        ShowTipSelection(tipState) { selectedTip ->
            tipState = selectedTip
        }
        Button(onClick = {
            result = bill.toInt() + tipState
        }) { Text("Calculate") }
        Text("Total Bill: $result")
    }
}

@Composable
fun ShowTipSelection(tipState: Int, onTipSelected: (Int) -> Unit) {
    Row {
        tips.forEach { tip ->
            FilterChip(
                selected = tipState == tip,
                onClick = { onTipSelected(tip) },
                label = { Text("$tip") })
        }
    }
}

val tips = listOf(10, 15, 20)


@Preview(showBackground = true)
@Composable
private fun PreviewTipCalculator() {
    TipCalculator()
}