package hr.ferit.marija.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import hr.ferit.marija.project1.navigation.AppNavigation
import hr.ferit.marija.project1.screens.CategoryScreen
import hr.ferit.marija.project1.screens.RegionsScreen
import hr.ferit.marija.project1.ui.theme.MyApplicationTheme
import hr.ferit.marija.project1.viewmodel.LocationsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Inicijalizacija ViewModela koji upravlja svim podacima (Firebase)
                val viewModel: LocationsViewModel = viewModel()

                // Pokretanje navigacije
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

// --- PREVIEWS ---
// Ovdje popravljamo nazive funkcija da se podudaraju s tvojim novim ekranima

@Preview(showBackground = true)
@Composable
fun RegionsScreenPreview() { // Promijenjeno iz HomeScreenPreview
    val navController = rememberNavController()
    RegionsScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    val navController = rememberNavController()
    // Usklađeno s novim engleskim parametrom 'region'
    CategoryScreen(
        navController = navController,
        region = "Dalmacija"
    )
}