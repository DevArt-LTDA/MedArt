package medart.app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
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

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var rut by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }

    // Para hacer UPDATE aunque el usuario cambie el RUT
    var rutOriginal by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(profile?.rut) {
        if (profile != null) {
            nombre = profile.nombre
            apellido = profile.apellido
            rut = profile.rut
            email = profile.email
            telefono = profile.telefono
            rutOriginal = profile.rut
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil del paciente") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
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
                Text("Datos del perfil", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nuevo -> nombre = nuevo },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = apellido,
                    onValueChange = { nuevo -> apellido = nuevo },
                    label = { Text("Apellido") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = rut,
                    onValueChange = { nuevo -> rut = nuevo },
                    label = { Text("RUT") },
                    modifier = Modifier.fillMaxWidth()
                    // Si NO quiere permitir editar el RUT:
                    // , enabled = false
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { nuevo -> email = nuevo },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { nuevo -> telefono = nuevo },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        profileViewModel.updateProfile(
                            currentRut = rutOriginal.trim(),
                            newRut = rut.trim(),
                            nombre = nombre.trim(),
                            apellido = apellido.trim(),
                            email = email.trim(),
                            telefono = telefono.trim()
                        )
                        rutOriginal = rut.trim()
                    }
                ) {
                    Text("Guardar cambios")
                }
            } else {
                Text("No hay información de perfil registrada.")
            }

            Spacer(Modifier.height(24.dp))

            Text("Mis reservas", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))

            if (reservations.isEmpty()) {
                Text("No tiene reservas registradas.")
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
                                Text("Previsión: ${res.prevision}")
                                Text("Especialidad: ${res.especialidad}")
                                Text("Centro: ${res.centro}")
                                Text("Fecha: ${res.fecha}")
                                Text("Hora: ${res.hora}")
                            }
                        }
                    }
                }
            }
        }
    }
}
