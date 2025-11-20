package medart.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.R
import medart.app.ui.components.Dropdown
import medart.app.viewmodel.AppointmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    appointmentViewModel: AppointmentViewModel,
    onConfirmReserva: () -> Unit,
    onBack: () -> Unit = {}
) {
    val uiState by appointmentViewModel.uiState.collectAsState()

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    val isFormValid = uiState.isFormValid

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = topBlue
                        )
                    }
                },
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

            Text(
                text = "Reserva tu atención",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Selecciona tu previsión, especialidad y centro médico para continuar.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF555555)
            )

            Spacer(Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                tonalElevation = 2.dp,
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Datos de la reserva",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = topBlue
                    )

                    Spacer(Modifier.height(16.dp))

                    Dropdown(
                        label = "Previsión de salud",
                        selectedValue = uiState.prevision,
                        options = listOf("Fonasa", "Isapre", "Particular"),
                        onSelect = { appointmentViewModel.onPrevisionChange(it) }
                    )

                    Dropdown(
                        label = "Especialidad",
                        selectedValue = uiState.especialidad,
                        options = listOf(
                            "Medicina General",
                            "Pediatría",
                            "Cardiología",
                            "Traumatología"
                        ),
                        onSelect = { appointmentViewModel.onEspecialidadChange(it) }
                    )


                    Dropdown(
                        label = "Centro médico",
                        selectedValue = uiState.centro,
                        options = listOf(
                            "Clinica Santiago",
                            "Centro Médico Maipú",
                            "Clinica La Florida"
                        ),
                        onSelect = { appointmentViewModel.onCentroChange(it) }
                    )

                    Dropdown(
                        label = "Fecha",
                        selectedValue = uiState.fecha,
                        options = listOf(
                            "8/11/2025",
                            "10/12/2025",
                            "17/11/2025",
                            "20/11/2025",
                            "30/12/2026"
                        ),
                        onSelect = { appointmentViewModel.onFechaChange(it) }
                    )

                    Dropdown(
                        label = "Hora",
                        selectedValue = uiState.hora,
                        options = listOf("9:00 AM", "11:30 AM", "10:20 AM", "3:00 PM", "1:00 PM"),
                        onSelect = { appointmentViewModel.onHoraChange(it) }
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            appointmentViewModel.onEnviarReserva()
                            if (isFormValid) onConfirmReserva()
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
                        Text("Confirmar reserva")
                    }
                }
            }
        }
    }
}
