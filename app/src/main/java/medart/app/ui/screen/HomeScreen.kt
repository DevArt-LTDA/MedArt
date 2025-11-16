package medart.app.ui.screen

import androidx.compose.foundation.layout.*
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

@Composable
fun HomeScreen(
    profileViewModel: UserProfileViewModel,
    onConsultaMedicaClick: () -> Unit,
    onVerPerfil: () -> Unit,
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
                text = "Bienvenido al sistema de agendamiento médico",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
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
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}
