package medart.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onContinue: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    // regla simple: mínimo 6 caracteres
    val isPasswordValid = password.length >= 6

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        // MISMO LOGO QUE EN RutScreen
                        Image(
                            painter = painterResource(id = R.drawable.logo_devart_sin_fondo),
                            contentDescription = "Logo MedArt",
                            modifier = Modifier
                                .height(32.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "MedArt",
                            color = topBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        // CONTENIDO CENTRADO IGUAL QUE EN RutScreen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgLight)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // -------- TÍTULO --------
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Inicia sesión ")
                        }
                    },
                    fontSize = 26.sp,
                    color = Color(0xFF222222),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Ingresa tu contraseña para acceder al portal.",
                    fontSize = 15.sp,
                    color = Color(0xFF555555),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(28.dp))

                // -------- CONTRASEÑA --------
                Text(
                    text = "Contraseña *",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = topBlue,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(6.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ingresa tu contraseña") },
                    singleLine = true,
                    visualTransformation = if (showPassword)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,
                                contentDescription = if (showPassword)
                                    "Ocultar contraseña"
                                else
                                    "Mostrar contraseña"
                            )
                        }
                    }
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Recuerda no compartir tu contraseña con nadie.",
                    fontSize = 12.sp,
                    color = Color(0xFF777777),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(28.dp))

                // -------- BOTÓN CONTINUAR --------
                Button(
                    onClick = { if (isPasswordValid) onContinue() },
                    enabled = isPasswordValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Iniciar sesión")
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 13.sp,
                    color = topBlue,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                TextButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("¿No tienes una cuenta? Regístrate")
                }


            }
        }
    }
}
