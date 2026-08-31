package hr.ferit.marija.project1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.ferit.marija.project1.screens.CategoryScreen
import hr.ferit.marija.project1.screens.LocationDetailsScreen
import hr.ferit.marija.project1.screens.LocationsListScreen
import hr.ferit.marija.project1.viewmodel.LocationsViewModel
import hr.ferit.marija.project1.screens.MyListScreen
import hr.ferit.marija.project1.screens.RegionsScreen

const val REGIONS_SCREEN = "regions"
const val CATEGORIES_SCREEN = "categories/{region}"
const val LOCATIONS_LIST_SCREEN = "locations/{region}/{category}"
const val MY_LIST_SCREEN = "my_list"
const val DETAILS_SCREEN = "details/{locationId}"

@Composable
fun AppNavigation(
    viewModel: LocationsViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = REGIONS_SCREEN
    ) {
        composable(REGIONS_SCREEN) {
            RegionsScreen(navController)
        }

        composable(CATEGORIES_SCREEN) { backStackEntry ->
            val region = backStackEntry.arguments?.getString("region") ?: ""
            CategoryScreen(navController, region)
        }

        composable(LOCATIONS_LIST_SCREEN) { backStackEntry ->
            val region = backStackEntry.arguments?.getString("region") ?: ""
            val category = backStackEntry.arguments?.getString("category") ?: ""
            LocationsListScreen(navController, viewModel, region, category)
        }

        composable(MY_LIST_SCREEN) {
            MyListScreen(navController, viewModel)
        }

        composable(DETAILS_SCREEN) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("locationId") ?: ""
            LocationDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                locationId = id
            )
        }
    }
}