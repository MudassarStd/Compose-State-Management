package com.std.composestatemanagement.challenges.stopwatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun StopwatchHost() {
    // hoisted states
    var time by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    val timeLapsed = remember { mutableStateListOf<Int>() }

    Stopwatch(
        time = time,
        lapsed = timeLapsed,
        isRunning = isRunning,
        onStart = { isRunning = true },
        onStop = { isRunning = false },
        onTick = { time++ },
        onBookmark = { timeLapsed.add(time) },
        onReset = {
            time = 0
            timeLapsed.clear()
        }
    )
}

@Composable
fun Stopwatch(
    time: Int,
    lapsed: List<Int>,
    isRunning: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onTick: () -> Unit,
    onBookmark: () -> Unit,
    onReset: () -> Unit
) {

    // launches a coroutine tied to this comp's lifecycle, based on isRunning flag.
    // will be launched on isRunning value change
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            onTick()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Time: $time sec", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(lapsed) {
                Text("Timestamp: $it")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Button(onClick = {
                onStart()
            }, enabled = !isRunning) { Text("Start") }


            Button(onClick = {
                onStop()
            }, enabled = isRunning) { Text("Stop") }


            Button(onClick = {
                onBookmark()
            }) { Text("Bookmark") }

            Button(onClick = {
                onReset()
            }) { Text("Reset") }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun StopwatchPreview() {
    StopwatchHost()
}