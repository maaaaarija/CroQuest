package hr.ferit.marija.project1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.marija.project1.components.LocationCard
import hr.ferit.marija.project1.viewmodel.LocationsViewModel

@Composable
fun LocationsListScreen(
    navController: NavController,
    viewModel: LocationsViewModel,
    region: String,
    category: String
) {

    val filteredLocations = remember(region, category, viewModel.allLocations.size) {
        viewModel.allLocations.filter {
            it.regija == region && it.kategorija == category
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF1976D2))
            }
            Text(
                text = "$region - $category",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredLocations.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF1976D2))
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredLocations) { location ->
                    LocationCard(
                        lokacija = location,
                        onAction = { viewModel.addToMyList(location) },
                        icon = Icons.Default.Add,
                        onCardClick = {
                            navController.navigate("details/${location.id}")
                        }
                    )
                }
            }
        }

        OutlinedButton(
            onClick = {
                navController.navigate("my_list")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1976D2))
        ) {
            Icon(imageVector = Icons.Default.List, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("MOJA LISTA", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}