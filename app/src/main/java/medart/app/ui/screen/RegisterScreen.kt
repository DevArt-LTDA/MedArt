package medart.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.ui.components.InputText
import medart.app.viewmodel.UserProfileViewModel

@Composable
fun RegisterScreen(
    profileViewModel: UserProfileViewModel,
    onRegistered: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de paciente",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputText(
            value = nombre,
            onValueChange = { nombre = it },
            label = "Nombre"
        )
        Spacer(modifier = Modifier.height(12.dp))

        InputText(
            value = apellido,
            onValueChange = { apellido = it },
            label = "Apellido"
        )
        Spacer(modifier = Modifier.height(12.dp))

        InputText(
            value = email,
            onValueChange = { email = it },
            label = "Correo electrónico"
        )
        Spacer(modifier = Modifier.height(12.dp))

        InputText(
            value = telefono,
            onValueChange = { telefono = it },
            label = "Teléfono"
        )
        Spacer(modifier = Modifier.height(12.dp))

        InputText(
            value = rut,
            onValueChange = { rut = it },
            label = "RUT"
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Contraseña
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                profileViewModel.saveProfile(
                    nombre = nombre,
                    apellido = apellido,
                    email = email,
                    telefono = telefono,
                    rut = rut,
                    password = password
                )
                onRegistered()
            },
            enabled = nombre.isNotBlank()
                    && apellido.isNotBlank()
                    && email.isNotBlank()
                    && rut.isNotBlank()
                    && password.isNotBlank(),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Guardar y continuar")
        }
    }
}
