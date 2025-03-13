package com.std.composestatemanagement.presentation


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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// ====================== Tdo list ======================

@Composable
fun TodoScreen(modifier: Modifier = Modifier) {

    // hoisted state (we have this state list)
    val todoItems = remember { mutableStateListOf<TodoItem>() }

    Column (modifier = Modifier.fillMaxSize()) {
        ChildTodo(onAdd = { item -> todoItems.add(item) }, onRemove = { todoItems.removeLast() })
        Spacer(Modifier.height(12.dp))
        Text("Todo Items", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(todoItems) { item ->
                TodoItem(item) { checked ->
                    todoItems[todoItems.indexOf(item)] = item.copy(isComplete = checked)
                }
            }
        }
    }
}

@Composable
fun ChildTodo(onAdd: (TodoItem) -> Unit, onRemove: () -> Unit) {
    var text by remember { mutableStateOf("") }
    Column {
        TextField(value = text, onValueChange = { text = it })
        Row {
            Button(onClick = {
                onAdd(TodoItem(text))
                text = ""
            }) { Text("Add") }
            Button(onClick = onRemove) { Text("Remove") }
        }
    }
}

@Composable
fun TodoItem(todoItem: TodoItem, onToggleComplete: (Boolean) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(todoItem.text, textDecoration = if (todoItem.isComplete) TextDecoration.LineThrough else TextDecoration.None)
        Checkbox(checked = todoItem.isComplete, onCheckedChange = onToggleComplete)
    }
}


data class TodoItem(val text: String = "", var isComplete: Boolean = false)

@Preview(showBackground = true)
@Composable
private fun PreviewChallengeScreen() {
    TodoScreen()
}
