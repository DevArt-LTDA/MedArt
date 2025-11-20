package medart.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import medart.app.viewmodel.AppointmentViewModel
import medart.app.model.data.config.AppDatabase
import medart.app.model.data.repository.RegisterRepository
import medart.app.ui.screen.AppointmentScreen
import medart.app.ui.screen.HomeScreen
import medart.app.ui.screen.ProfileScreen
import medart.app.ui.screen.RegisterScreen
import medart.app.ui.screen.LoginScreen
import medart.app.ui.screen.RutScreen
import medart.app.viewmodel.RegisterViewModel
import medart.app.viewmodel.RegisterViewModelFactory
import medart.app.viewmodel.UserProfileViewModel

object Routes {
    const val RUT = "rut"
    const val LOGIN = "login"
    const val REGISTER = "register"
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
        startDestination = Routes.RUT
    ) {

        // RUT -> LOGIN
        composable(Routes.RUT) {
            RutScreen(
                onContinue = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // LOGIN: contraseña + botón "Registrarse"
        composable(Routes.LOGIN) {
            LoginScreen(
                onContinue = {
                    // si más adelante validas credenciales, aquí vas al HOME
                    navController.navigate(Routes.HOME) {
                        // opcional: limpiar RUT y LOGIN del backstack
                        popUpTo(Routes.RUT) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            val context = LocalContext.current

            // Instancia única de la BD
            val db = remember { AppDatabase.getDatabase(context) }

            val repository = remember { RegisterRepository(db.userDao()) }
            val factory = remember { RegisterViewModelFactory(repository) }

            val registerViewModel: RegisterViewModel = viewModel(factory = factory)

            RegisterScreen(
                profileViewModel = profileViewModel,
                registerViewModel = registerViewModel,
                onRegistered = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.RUT) { inclusive = true }
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
                onExamenesClick = {
                    // TODO: navegar a pantalla de Exámenes cuando la crees
                    // navController.navigate(Routes.EXAMENES)
                },
                onVerPerfil = { navController.navigate(Routes.PROFILE) },
                onLogout = { navController.navigate(Routes.RUT) }
            )
        }




// Confirmar hora -> guarda reserva y vuelve a HOME
        composable(Routes.APPOINTMENT) {
            val appointmentViewModel: AppointmentViewModel = viewModel()

            AppointmentScreen(
                appointmentViewModel = appointmentViewModel,
                onConfirmReserva = {
                    navController.popBackStack(Routes.HOME, inclusive = false)
                },
                onBack = { navController.popBackStack() }
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
