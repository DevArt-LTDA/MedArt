package medart.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import medart.app.viewmodel.UserProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: UserProfileViewModel,
    onBack: () -> Unit
) {
    val profile = profileViewModel.profile
    val reservations = profileViewModel.reservations

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil del paciente") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (profile != null) {
                Text(
                    text = "${profile.nombre} ${profile.apellido}",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "RUT: ${profile.rut}")
                Text(text = "Correo: ${profile.email}")
                Text(text = "Teléfono: ${profile.telefono}")
            } else {
                Text("No hay información de perfil registrada.")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Mis reservas",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (reservations.isEmpty()) {
                Text("No tiene reservas registradas.")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(reservations) { res ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Text(text = res.tipoAtencion)
                                Text(text = "Especialidad: ${res.especialidad}")
                                Text(text = "Horario: ${res.horario}")
                            }
                        }
                    }
                }
            }
        }
    }
}
