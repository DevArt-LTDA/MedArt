package medart.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import medart.app.viewmodel.RegisterViewModel
import medart.app.viewmodel.UserProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    profileViewModel: UserProfileViewModel,
    registerViewModel: RegisterViewModel,
    onRegistered: () -> Unit
) {
    // Estado que viene del ViewModel de registro
    val uiState by registerViewModel.uiState.collectAsState()

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    val isFormValid = uiState.isFormValid

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

            // Nombre
            InputText(
                value = uiState.nombre,
                onValueChange = { registerViewModel.onNombreChange(it) },
                label = "Nombre"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Apellido
            InputText(
                value = uiState.apellido,
                onValueChange = { registerViewModel.onApellidoChange(it) },
                label = "Apellido"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Email
            InputText(
                value = uiState.email,
                onValueChange = { registerViewModel.onEmailChange(it) },
                label = "Correo electrónico"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Teléfono
            InputText(
                value = uiState.telefono,
                onValueChange = { registerViewModel.onTelefonoChange(it) },
                label = "Teléfono"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // RUT
            InputText(
                value = uiState.rut,
                onValueChange = { registerViewModel.onRutChange(it) },
                label = "RUT"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { registerViewModel.onPasswordChange(it) },
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

            // Botón
            Button(
                onClick = {
                    // Guarda en Room (RegisterViewModel + RegisterRepository)
                    registerViewModel.onEnviarFormulario()

                    // Opcional: seguir guardando el perfil como antes
                    profileViewModel.saveProfile(
                        nombre = uiState.nombre,
                        apellido = uiState.apellido,
                        email = uiState.email,
                        telefono = uiState.telefono,
                        rut = uiState.rut,
                        password = uiState.password
                    )

                    if (uiState.isFormValid) {
                        onRegistered()
                    }
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
