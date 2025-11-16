package medart.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.viewmodel.UserProfileViewModel

@Composable
fun LoginScreen(
    profileViewModel: UserProfileViewModel,
    onContinue: () -> Unit
) {
    val profile = profileViewModel.profile

    var step by remember { mutableStateOf(1) }      // 1 = RUT, 2 = contraseña
    var rut by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (step == 1) "Iniciar sesión - Paso 1 de 2"
            else "Iniciar sesión - Paso 2 de 2",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (step == 1) {
            // Paso 1: pedir RUT
            OutlinedTextField(
                value = rut,
                onValueChange = { rut = it },
                label = { Text("RUT") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (profile == null) {
                        error = "No existe un usuario registrado. Regístrese primero."
                    } else if (rut != profile.rut) {
                        error = "RUT no encontrado."
                    } else {
                        error = null
                        step = 2
                    }
                },
                enabled = rut.isNotBlank(),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Continuar")
            }
        } else {
            // Paso 2: pedir contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Button(
                    onClick = {
                        // volver a pedir RUT
                        password = ""
                        error = null
                        step = 1
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Atrás")
                }

                Button(
                    onClick = {
                        if (profile == null) {
                            error = "No existe un usuario registrado."
                            step = 1
                        } else if (password != profile.password) {
                            error = "Contraseña incorrecta."
                        } else {
                            error = null
                            onContinue()
                        }
                    },
                    enabled = password.isNotBlank(),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ingresar")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                fontSize = 13.sp
            )
        }
    }
}
