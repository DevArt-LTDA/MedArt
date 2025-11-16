package medart.app.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import medart.app.domain.Reservation
import medart.app.ui.screen.AppointmentScreen
import medart.app.ui.screen.AuthScreen
import medart.app.ui.screen.HomeScreen
import medart.app.ui.screen.ProfileScreen
import medart.app.ui.screen.RegisterScreen
import medart.app.viewmodel.UserProfileViewModel
import medart.app.ui.screen.RutScreen
object Routes {
    const val AUTH = "auth"
    const val REGISTER = "register"
    const val LOGIN = "login"

    const val RUT = "rut"

    const val HOME = "home"
    const val APPOINTMENT = "appointment"
    const val PROFILE = "profile"
}

/**
 * Esta es la función que debe llamar MainActivity.
 */
@Composable
fun MedArtApp() {
    val navController = rememberNavController()
    val profileVm: UserProfileViewModel = viewModel()

    MedArtNavGraph(
        navController = navController,
        profileViewModel = profileVm
    )
}

@Composable
fun MedArtNavGraph(
    navController: NavHostController,
    profileViewModel: UserProfileViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.AUTH
    ) {
        // Pantalla inicial: elegir Iniciar sesión o Registrarse
        composable(Routes.AUTH) {
            AuthScreen(
                onLoginClick = { navController.navigate(Routes.RUT) },
                onRegisterClick = { navController.navigate(Routes.REGISTER) }
            )
        }

        // REGISTRO -> HOME
        composable(Routes.REGISTER) {
            RegisterScreen(
                profileViewModel = profileViewModel,
                onRegistered = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.AUTH) { inclusive = false }
                    }
                }
            )
        }

        composable(Routes.RUT) {
            RutScreen(
                onContinue = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.AUTH) { inclusive = false }
                    }
                }
            )
        }

        // HOME con navbar (perfil, cerrar sesión)
        composable(Routes.HOME) {
            HomeScreen(
                profileViewModel = profileViewModel,
                onConsultaMedicaClick = {
                    navController.navigate(Routes.APPOINTMENT)
                },
                onVerPerfil = {
                    navController.navigate(Routes.PROFILE)
                },
                onLogout = {
                    profileViewModel.logout()
                    navController.navigate(Routes.AUTH) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Confirmar hora -> guarda reserva y vuelve a HOME
        composable(Routes.APPOINTMENT) {
            AppointmentScreen(
                onConfirmReservation = { reservation: Reservation ->
                    profileViewModel.addReservation(reservation)
                    navController.popBackStack(Routes.HOME, inclusive = false)
                }
            )
        }

        // Perfil: ver datos y reservas
        composable(Routes.PROFILE) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
