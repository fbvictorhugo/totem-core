package dev.fbvictorhugo.totemcore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fbvictorhugo.totemcore.ui.screens.home.HomeScreen
import dev.fbvictorhugo.totemcore.ui.screens.interests.InterestsScreen
import dev.fbvictorhugo.totemcore.ui.screens.registration.RegistrationScreen
import dev.fbvictorhugo.totemcore.ui.screens.review.ReviewScreen

@Composable
fun TotemNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.name,
        modifier = modifier
    ) {

        composable(route = Screens.Home.name) {
            HomeScreen(
                onNavigateToRegistration = {
                    navController.navigate(Screens.Registration.name)
                }
            )
        }

        composable(route = Screens.Registration.name) {
            RegistrationScreen(
                onNavigateToInterests = {
                    navController.navigate(Screens.Interests.name)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screens.Interests.name) {
            InterestsScreen(
                onNavigateToReview = {
                    navController.navigate(Screens.Review.name)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screens.Review.name) {
            ReviewScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onFinalize = {
                    navController.navigate(Screens.Home.name) {
                        popUpTo(Screens.Home.name) { inclusive = true }
                    }
                }
            )
        }

    }
}
