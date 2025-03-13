package com.std.composestatemanagement.challenges.themeToggler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ParentThemeScreen(modifier: Modifier = Modifier) {

    var isDark by remember { mutableStateOf(false) }

    MaterialTheme(colorScheme = if (isDark) darkColorScheme() else lightColorScheme()) {
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {}) { Text("Button Theme") }
            ChildToggler(isDark) {
                isDark = it
            }
        }
    }

}

@Composable
fun ChildToggler(isDark: Boolean, onThemeToggle: (Boolean) -> Unit) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Text("Dark mode")
        Spacer(Modifier.width(12.dp))
        Switch(checked = isDark, onCheckedChange = onThemeToggle, )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewThemeToggle() {
    ParentThemeScreen()
}