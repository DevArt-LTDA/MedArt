package medart.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import medart.app.ui.components.InputText
import medart.app.ui.components.PrevisionDropdown
import medart.app.viewmodel.LoginViewModel

@Composable
fun RutScreen(
    viewModel: LoginViewModel = viewModel(),
    onContinue: () -> Unit
) {
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // RUT
        InputText(
            value = state.rut,
            onValueChange = viewModel::onRutChange,
            label = "RUT"
        )


        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onContinue,
            enabled = state.rut.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }
    }
}
