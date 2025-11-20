package medart.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.domain.Reservation   // <- ojo con el package de Reservation

@Composable
fun AppointmentCardSimple(reservation: Reservation) {
    val topBlue = Color(0xFF007ACC)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        tonalElevation = 2.dp,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Especialidad
            Text(
                text = reservation.especialidad,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            // Centro médico
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Centro médico",
                    tint = topBlue
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = reservation.centro,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(Modifier.height(12.dp))

            // Fecha y hora
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Fecha",
                        tint = topBlue
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(reservation.fecha)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Hora",
                        tint = topBlue
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(reservation.hora)
                }
            }
        }
    }
}
