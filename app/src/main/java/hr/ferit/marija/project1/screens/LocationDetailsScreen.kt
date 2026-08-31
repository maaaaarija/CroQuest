package hr.ferit.marija.project1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import hr.ferit.marija.project1.viewmodel.LocationsViewModel

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    viewModel: LocationsViewModel,
    locationId: String
) {
    val lokacija = viewModel.allLocations.find { it.id == locationId }

    if (lokacija == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF1976D2))
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Nazad",
                    tint = Color(0xFF1976D2)
                )
            }
        }

        AsyncImage(
            model = lokacija.image,
            contentDescription = lokacija.naziv,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lokacija.naziv,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                FloatingActionButton(
                    onClick = { viewModel.addToMyList(lokacija) },
                    containerColor = Color(0xFFC5D5F0)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Dodaj u favorite")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = Color(0xFFE8DEF8),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "${lokacija.regija} - ${lokacija.kategorija}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "o lokaciji:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = lokacija.opis,
                fontSize = 16.sp,
                color = Color.DarkGray,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}