package medart.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.R
import medart.app.ui.components.EmptyAppointmentsCard
import medart.app.ui.components.UpcomingAppointmentItem
import medart.app.viewmodel.AppointmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingAppointmentsScreen(
    appointmentViewModel: AppointmentViewModel,
    onBack: () -> Unit = {}
) {
    val citas by appointmentViewModel.reservas.collectAsState(initial = emptyList())

    val topBlue = Color(0xFF008CFF)
    val bgLight = Color(0xFFF7FBFF)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

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
                text = "Mis citas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Consulta los detalles de tus próximas citas médicas de forma rápida y sencilla.",
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
                        text = "Próximas citas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = topBlue
                    )

                    Spacer(Modifier.height(16.dp))

                    if (citas.isEmpty()) {
                        EmptyAppointmentsCard()
                    } else {
                        citas.forEach { cita ->
                            UpcomingAppointmentItem(
                                appointment = cita,
                                topBlue = topBlue
                            )
                            Spacer(Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}
