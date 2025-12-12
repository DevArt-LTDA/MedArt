package medart.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.model.data.entities.AppointmentEntities

@Composable
fun UpcomingAppointmentItem(
    appointment: AppointmentEntities,
    topBlue: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        tonalElevation = 1.dp,
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Text(
                text = "Cita #${appointment.id}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = appointment.especialidad,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = topBlue
            )

            Spacer(Modifier.height(8.dp))

            Text("Previsión: ${appointment.prevision}")
            Text("Centro: ${appointment.centro}")
            Text("Fecha: ${appointment.fecha}")
            Text("Hora: ${appointment.hora}")
        }
    }
}
