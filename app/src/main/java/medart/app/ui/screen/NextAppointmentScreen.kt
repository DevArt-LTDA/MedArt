package medart.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.R
import medart.app.domain.Reservation
import medart.app.ui.components.AppointmentCardSimple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextAppointmentScreen(
    reservations: List<Reservation>,
    onBack: () -> Unit = {}
) {
    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

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
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = bgLight
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Historial de atenciones",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Consulta los detalles de tus citas agendadas.",
                fontSize = 14.sp,
                color = Color(0xFF555555)
            )

            Spacer(Modifier.height(24.dp))

            reservations.forEach { reserva ->
                AppointmentCardSimple(reserva)
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}
