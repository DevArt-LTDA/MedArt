package medart.app.ui.screen

import medart.app.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutScreen(
    onContinue: () -> Unit
) {
    var rut by rememberSaveable { mutableStateOf("") }

    val rutRegex = Regex("^[0-9]{7,8}[0-9Kk]\$")
    val isRutValid = rutRegex.matches(rut)

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(id = R.drawable.logo_devart_sin_fondo),
                            contentDescription = "Logo MedArt",
                            modifier = Modifier
                                .size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "MedArt",
                            color = topBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgLight)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(150.dp))

            // -------- TÍTULO --------
            Text(
                text = buildAnnotatedString {
                    append("Mi ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Portal MedArt")
                    }
                },
                fontSize = 26.sp,
                color = Color(0xFF222222)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Ingresa tu Rut para iniciar sesión o crear una nueva cuenta",
                fontSize = 15.sp,
                color = Color(0xFF555555)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // -------- CAMPO RUT --------
            Text(
                text = "Rut *",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = topBlue
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = rut,
                onValueChange = { rut = it.uppercase() },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ej: 20345678K") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Ingresa tu Rut sin punto ni guión",
                fontSize = 12.sp,
                color = Color(0xFF777777)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // -------- BOTÓN CONTINUAR --------
            Button(
                onClick = { if (isRutValid) onContinue() },
                enabled = isRutValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Continuar")
            }

            Spacer(modifier = Modifier.height(32.dp))


            Spacer(modifier = Modifier.weight(1f))

            // ----- FOOTER -----
            Text(
                text = "MedArt 2025",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                color = Color(0xFF999999)
            )
        }
    }
}
