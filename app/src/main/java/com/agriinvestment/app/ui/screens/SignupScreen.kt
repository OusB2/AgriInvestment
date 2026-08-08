package com.agriinvestment.app.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agriinvestment.app.ui.theme.*

private data class SignupData(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val country: String = "Côte d'Ivoire",
    val phone: String = "",
    val commune: String = "",
    val budget: String = ""
)

private val countries = listOf(
    "Côte d'Ivoire", "Sénégal", "Mali", "Burkina Faso", "Bénin", "Togo", "Ghana"
)

private val budgetOptions = listOf(
    "5 000 FCFA", "10 000 FCFA", "25 000 FCFA", "50 000 FCFA", "100 000 FCFA"
)

@Composable
fun SignupScreen(
    onAccountCreated: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var step by remember { mutableStateOf(1) }
    var data by remember { mutableStateOf(SignupData()) }
    var passwordVisible by remember { mutableStateOf(false) }
    var customBudget by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(24.dp)
    ) {
        // En-tête : bouton retour + indicateur d'étapes
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (step > 1) {
                IconButton(onClick = { step -= 1 }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = TextPrimary)
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            StepIndicator(currentStep = step, totalSteps = 3)

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(28.dp))

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInHorizontally(tween(300)) { it } togetherWith
                            slideOutHorizontally(tween(300)) { -it })
                } else {
                    (slideInHorizontally(tween(300)) { -it } togetherWith
                            slideOutHorizontally(tween(300)) { it })
                }
            },
            label = "signup_step"
        ) { currentStep ->
            when (currentStep) {
                1 -> StepOneIdentity(
                    data = data,
                    passwordVisible = passwordVisible,
                    onTogglePassword = { passwordVisible = !passwordVisible },
                    onDataChange = { data = it }
                )
                2 -> StepTwoLocation(
                    data = data,
                    onDataChange = { data = it }
                )
                else -> StepThreeBudget(
                    data = data,
                    customBudget = customBudget,
                    onCustomBudgetToggle = { customBudget = it },
                    onDataChange = { data = it }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val canContinue = when (step) {
            1 -> data.fullName.isNotBlank() && data.password.isNotBlank()
            2 -> data.phone.isNotBlank() && data.commune.isNotBlank()
            else -> data.budget.isNotBlank()
        }

        Button(
            onClick = {
                if (step < 3) {
                    step += 1
                } else {
                    onAccountCreated()
                }
            },
            enabled = canContinue,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = ForestGreen),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                if (step < 3) "Suivant" else "S'inscrire",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }

        if (step == 1) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Déjà un compte ? ", color = TextSecondary, fontSize = 14.sp)
                Text(
                    "Se connecter",
                    color = ForestGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNavigateToLogin
                    )
                )
            }
        }
    }
}

@Composable
private fun StepIndicator(currentStep: Int, totalSteps: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..totalSteps) {
            val isDone = i < currentStep
            val isCurrent = i == currentStep

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(if (isDone || isCurrent) ForestGreen else LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (isDone) {
                    Icon(Icons.Filled.Check, contentDescription = null, tint = White, modifier = Modifier.size(16.dp))
                } else {
                    Text(
                        "$i",
                        color = if (isCurrent) White else TextSecondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            if (i < totalSteps) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(2.dp)
                        .background(if (isDone) ForestGreen else Divider)
                )
            }
        }
    }
}

@Composable
private fun StepOneIdentity(
    data: SignupData,
    passwordVisible: Boolean,
    onTogglePassword: () -> Unit,
    onDataChange: (SignupData) -> Unit
) {
    Column {
        Text(
            "Créer mon compte",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(
            "Entrez vos informations",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignupField(
            value = data.fullName,
            onValueChange = { onDataChange(data.copy(fullName = it)) },
            placeholder = "Nom complet"
        )

        Spacer(modifier = Modifier.height(14.dp))

        SignupField(
            value = data.email,
            onValueChange = { onDataChange(data.copy(email = it)) },
            placeholder = "Email (optionnel)",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(14.dp))

        SignupField(
            value = data.password,
            onValueChange = { onDataChange(data.copy(password = it)) },
            placeholder = "Mot de passe",
            isPassword = true,
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = onTogglePassword
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StepTwoLocation(
    data: SignupData,
    onDataChange: (SignupData) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            "Où êtes-vous situé ?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(
            "Cela nous aide à vous proposer les bons projets",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = data.country,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Pays", color = TextSecondary) },
                trailingIcon = {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null, tint = TextSecondary)
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = LightGray,
                    focusedContainerColor = LightGray,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = ForestGreen
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(country) },
                        onClick = {
                            onDataChange(data.copy(country = country))
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        SignupField(
            value = data.phone,
            onValueChange = { onDataChange(data.copy(phone = it)) },
            placeholder = "Numéro de téléphone",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(14.dp))

        SignupField(
            value = data.commune,
            onValueChange = { onDataChange(data.copy(commune = it)) },
            placeholder = "Commune"
        )
    }
}

@Composable
private fun StepThreeBudget(
    data: SignupData,
    customBudget: Boolean,
    onCustomBudgetToggle: (Boolean) -> Unit,
    onDataChange: (SignupData) -> Unit
) {
    Column {
        Text(
            "Votre budget de départ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(
            "Combien souhaitez-vous investir pour commencer ?",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        budgetOptions.forEach { option ->
            val selected = data.budget == option && !customBudget
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (selected) ForestGreen.copy(alpha = 0.1f) else LightGray)
                    .clickable {
                        onCustomBudgetToggle(false)
                        onDataChange(data.copy(budget = option))
                    }
                    .padding(vertical = 16.dp, horizontal = 20.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        option,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) ForestGreen else TextPrimary
                    )
                    if (selected) {
                        Icon(Icons.Filled.Check, contentDescription = null, tint = ForestGreen, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        SignupField(
            value = if (customBudget) data.budget else "",
            onValueChange = {
                onCustomBudgetToggle(true)
                onDataChange(data.copy(budget = it))
            },
            placeholder = "Autre montant (FCFA)",
            keyboardType = KeyboardType.Number
        )
    }
}

@Composable
private fun SignupField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextSecondary) },
        trailingIcon = {
            if (isPassword && onTogglePasswordVisibility != null) {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = TextSecondary
                    )
                }
            }
        },
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = LightGray,
            focusedContainerColor = LightGray,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = ForestGreen
        ),
        modifier = Modifier.fillMaxWidth()
    )
}