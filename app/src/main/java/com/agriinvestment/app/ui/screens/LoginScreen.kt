package com.agriinvestment.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agriinvestment.app.R
import com.agriinvestment.app.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Fond dégradé en haut avec le logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(ForestGreenDark, ForestGreen)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_agriinvestment),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Bienvenue !",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "Reconnectez-vous à votre compte",
                    fontSize = 14.sp,
                    color = White.copy(alpha = 0.85f)
                )
            }
        }

        // Carte blanche arrondie qui remonte sur le dégradé
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.78f)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(White)
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            ModernTextField(
                value = phone,
                onValueChange = { phone = it },
                placeholder = "Numéro de téléphone",
                leadingIcon = Icons.Filled.Phone
            )

            Spacer(modifier = Modifier.height(16.dp))

            ModernTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Mot de passe",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* TODO: mot de passe oublié */ }) {
                    Text(
                        "Mot de passe oublié ?",
                        color = ForestGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onLoginSuccess,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = ForestGreen),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(50), spotColor = ForestGreen)
            ) {
                Text("Connexion", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Divider)
                Text(
                    "  ou continuer avec  ",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Divider)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircleIconButton(
                    onClick = { /* TODO: Google */ },
                    backgroundColor = LightGray
                ) {
                    Text("G", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextPrimary)
                }

                Spacer(modifier = Modifier.width(20.dp))

                CircleIconButton(
                    onClick = { /* TODO: biométrie */ },
                    backgroundColor = LightGray
                ) {
                    Icon(
                        imageVector = Icons.Filled.Fingerprint,
                        contentDescription = "Connexion biométrique",
                        tint = ForestGreen,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Pas encore de compte ? ", color = TextSecondary, fontSize = 14.sp)
                Text(
                    "S'inscrire",
                    color = ForestGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNavigateToSignup
                    )
                )
            }
        }
    }
}

@Composable
private fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextSecondary) },
        leadingIcon = {
            Icon(leadingIcon, contentDescription = null, tint = ForestGreen)
        },
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
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = LightGray,
            focusedContainerColor = LightGray,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = ForestGreen
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CircleIconButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}