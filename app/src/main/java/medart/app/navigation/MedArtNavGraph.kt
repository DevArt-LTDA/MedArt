package medart.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import medart.app.ui.screen.AppointmentScreen
import medart.app.ui.screen.LoginScreen

object Routes {
    const val LOGIN = "login"
    const val APPOINTMENT = "appointment"
}

@Composable
fun MedArtApp() {
    val navController = rememberNavController()
    MedArtNavGraph(navController = navController)
}

@Composable
fun MedArtNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onContinue = {
                    navController.navigate(Routes.APPOINTMENT)
                }
            )
        }
        composable(Routes.APPOINTMENT) {
            AppointmentScreen()
        }
    }
}
