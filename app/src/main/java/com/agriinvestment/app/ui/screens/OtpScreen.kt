package com.agriinvestment.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.agriinvestment.app.ui.components.PrimaryButton
import com.agriinvestment.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(onVerified: () -> Unit) {
    var codeDigits by remember { mutableStateOf(List(6) { "" }) }
    var secondsLeft by remember { mutableStateOf(60) }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    LaunchedEffect(secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft -= 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Vérification OTP",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Entrez le code à 6 chiffres envoyé par SMS",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            codeDigits.forEachIndexed { index, digit ->
                OutlinedTextField(
                    value = digit,
                    onValueChange = { value ->
                        val sanitized = value.filter { it.isDigit() }.take(1)
                        codeDigits = codeDigits.toMutableList().also { it[index] = sanitized }
                        if (sanitized.isNotEmpty() && index < focusRequesters.lastIndex) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusRequesters[index]),
                    textStyle = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    colors = TextFieldDefaults.colors()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (secondsLeft > 0) {
            Text(
                text = "Renvoyer le code dans 00:${secondsLeft.toString().padStart(2, '0')}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        } else {
            TextButton(onClick = { secondsLeft = 60 }) {
                Text("Renvoyer le code", color = ForestGreen, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "Vérifier", onClick = onVerified)

        Spacer(modifier = Modifier.height(16.dp))
    }
}
