package dev.fbvictorhugo.totemcore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dev.fbvictorhugo.totemcore.ui.screens.ConclusionScreen
import dev.fbvictorhugo.totemcore.ui.screens.HomeScreen
import dev.fbvictorhugo.totemcore.ui.screens.InterestsScreen
import dev.fbvictorhugo.totemcore.ui.screens.RegistrationScreen
import dev.fbvictorhugo.totemcore.ui.screens.ReviewScreen
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationViewModel

@Composable
fun TotemNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home,
        modifier = modifier
    ) {
        composable<Screens.Home> {
            HomeScreen(
                onNavigateToRegistration = {
                    navController.navigate(Screens.RegistrationWizard)
                }
            )
        }

        navigation<Screens.RegistrationWizard>(
            startDestination = Screens.Registration
        ) {
            composable<Screens.Registration> { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry<Screens.RegistrationWizard>()
                }
                val sharedViewModel: SharedRegistrationViewModel =
                    viewModel(parentEntry) {
                        SharedRegistrationViewModel()
                    }

                RegistrationScreen(
                    sharedViewModel = sharedViewModel,
                    onNavigateToInterests = { navController.navigate(Screens.Interests) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable<Screens.Interests> { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry<Screens.RegistrationWizard>()
                }
                val sharedViewModel: SharedRegistrationViewModel = viewModel(parentEntry) {
                    SharedRegistrationViewModel()
                }

                InterestsScreen(
                    sharedViewModel = sharedViewModel,
                    onNavigateToReview = { navController.navigate(Screens.Review) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable<Screens.Review> { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry<Screens.RegistrationWizard>()
                }
                val sharedViewModel: SharedRegistrationViewModel = viewModel(parentEntry) {
                    SharedRegistrationViewModel()
                }

                ReviewScreen(
                    sharedViewModel = sharedViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onFinalize = { navController.navigate(Screens.Conclusion) },
                )
            }
        }

        composable<Screens.Conclusion> {
            ConclusionScreen(
                onConclude = {
                    navController.navigate(Screens.Home) {
                        popUpTo(Screens.Home) { inclusive = true }
                    }
                }
            )
        }
    }
}