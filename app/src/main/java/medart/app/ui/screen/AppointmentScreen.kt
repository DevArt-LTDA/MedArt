package medart.app.ui.screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import medart.app.domain.Reservation
@Composable
fun AppointmentScreen(onConfirmReservation: (Reservation) -> Unit) {

    // Especialidades de ejemplo
    val specialties = listOf(
        "Medicina general",
        "Pediatría",
        "Ginecología",
        "Traumatología",
        "Psicólogo",
        "Psiquiatra",
        "Kinesiología",
        "Otro"
    )

    // Horarios de ejemplo
    val times = listOf(
        "09:00", "09:30", "10:00", "10:30",
        "11:00", "11:30", "12:00",
        "15:00", "15:30", "16:00", "16:30", "17:00"
    )

    var selectedSpecialty by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // Dropdown especialidad
        DropdownField(
            label = "Especialidad",
            value = selectedSpecialty,
            options = specialties,
            onValueChange = { selectedSpecialty = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown horario
        DropdownField(
            label = "Horario",
            value = selectedTime,
            options = times,
            onValueChange = { selectedTime = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Aquí podrás guardar la hora médica o navegar a un resumen
            },
            enabled = selectedSpecialty.isNotBlank() && selectedTime.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar hora")
        }
    }

    Button(
        onClick = {
            val reservation = Reservation(
                id = (System.currentTimeMillis() and 0xFFFFFFF).toInt(),
                especialidad = selectedSpecialty,
                horario = selectedTime
            )
            onConfirmReservation(reservation)
        },
        enabled = selectedSpecialty.isNotBlank() && selectedTime.isNotBlank(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Confirmar hora")
    }

}

/**
 * Campo genérico con Dropdown usando material3.DropdownMenu
 */
@Composable
private fun DropdownField(
    label: String,
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
