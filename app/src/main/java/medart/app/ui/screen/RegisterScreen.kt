package medart.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.R
import medart.app.ui.components.InputText
import medart.app.viewmodel.UserProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    val isFormValid =
        nombre.isNotBlank() &&
                apellido.isNotBlank() &&
                email.isNotBlank() &&
                telefono.isNotBlank() &&
                rut.isNotBlank() &&
                password.isNotBlank()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(Color.White),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_devart_sin_fondo),
                            contentDescription = "Logo MedArt",
                            modifier = Modifier.height(32.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "MedArt",
                            color = topBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            )
        },
        containerColor = bgLight
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            // Título y subtítulo (mismo estilo que login)
            Text(
                text = "Registro de paciente",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Completa tus datos para crear tu cuenta en MedArt.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF555555)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campos de formulario
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recuerda no compartir tu contraseña con nadie.",
                fontSize = 12.sp,
                color = Color(0xFF777777),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón grande tipo MedArt / Bupa
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
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) topBlue else Color(0xFFE0E0E0),
                    contentColor = if (isFormValid) Color.White else Color(0xFF999999)
                )
            ) {
                Text("Guardar y continuar")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
