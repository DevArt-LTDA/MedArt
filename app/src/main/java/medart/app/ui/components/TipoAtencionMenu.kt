package medart.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TipoAtencionMenu(
    onConsultaMedicaClick: () -> Unit,
    onExamenesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFFE4F4FF),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "¿Qué tipo de atención necesitas?",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF004B7A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dos tarjetas en una fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // --- Consulta Médica ---
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(120.dp)
                        .clickable { onConsultaMedicaClick() },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MedicalServices,
                            contentDescription = "Consulta médica",
                            tint = Color(0xFF007ACC)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Consulta\nMédica",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // --- Exámenes ---
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(120.dp)
                        .clickable { onExamenesClick() },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Science,
                            contentDescription = "Exámenes",
                            tint = Color(0xFF007ACC)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Exámenes",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

