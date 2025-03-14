package com.std.composestatemanagement.challenges.passwordStrengthChecker

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

// password state should be hoisted in parent
// password form should be like

@Composable
fun PasswordCheckParent(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }


    val strength = when {
        password.length < 6 -> PasswordStrength.Weak
        password.length in 6..10 -> PasswordStrength.Medium
        else -> { PasswordStrength.Strong }
    }

    FormUI(strength, password) { changedText ->
        password = changedText
    }
}


@Composable
fun FormUI(strength: PasswordStrength, password: String, onPasswordChange: (String) -> Unit) {

    var hidePassword by remember { mutableStateOf(false) }

    Column {
        TextField(value = password, onValueChange = { onPasswordChange(it) }, supportingText = {
            Text(
                if (password.isNotEmpty()) {
                    when(strength) {
                        PasswordStrength.Weak -> "weak"
                        PasswordStrength.Medium -> "medium"
                        PasswordStrength.Strong -> "strong"
                    }
                } else {
                    ""
                }

            )
        }, trailingIcon = { IconButton(onClick = { hidePassword = !hidePassword }) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)

            }
        },
            visualTransformation = if (hidePassword) PasswordVisualTransformation() else VisualTransformation.None )

    }
}

enum class PasswordStrength {
    Weak, Medium, Strong
}

@Preview(showBackground = true)
@Composable
private fun PasswordStrengthChecker() {
    PasswordCheckParent()
}