package medart.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import medart.app.ui.components.ProfileTopBar
import medart.app.ui.components.TipoAtencionMenu
import medart.app.viewmodel.UserProfileViewModel
import medart.app.ui.components.QuickAccessCard

@Composable
fun HomeScreen(
    profileViewModel: UserProfileViewModel,
    onConsultaMedicaClick: () -> Unit,
    onExamenesClick: () -> Unit,
    onVerPerfil: () -> Unit,
    onProximasCitasClick: () -> Unit,
    onLogout: () -> Unit
) {
    val profile = profileViewModel.profile

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileTopBar(
            profile = profile,
            onVerPerfil = onVerPerfil,
            onLogout = onLogout,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = profile?.let { "¡Hola ${it.nombre} ${it.apellido}!" } ?: "¡Hola Usuario invitado!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "Seleccione el tipo de atención para continuar.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            TipoAtencionMenu(
                onConsultaMedicaClick = onConsultaMedicaClick,
                onExamenesClick = onExamenesClick,
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Accesos rápidos",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(20.dp))

            QuickAccessCard(
                title = "Próximas citas",
                subtitle = "Revisa el detalle de tus reservas",
                icon = Icons.Default.CalendarToday,
                onClick = onProximasCitasClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            QuickAccessCard(
                title = "Historial de citas",
                subtitle = "Accede a tus visitas anteriores",
                icon = Icons.Default.History,
                modifier = Modifier.fillMaxWidth()
            )


        }
    }
}
