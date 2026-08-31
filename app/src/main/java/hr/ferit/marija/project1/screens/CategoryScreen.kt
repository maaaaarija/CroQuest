package hr.ferit.marija.project1.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.marija.project1.R

@Composable
fun CategoryScreen(navController: NavController, region: String) {
    val categories = listOf("muzeji", "galerije", "prirodne ljepote", "povijesne lokacije", "restorani")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Croatia\nQuest",
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 48.sp,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.width(40.dp))

            Image(
                painter = painterResource(id = R.drawable.karta_hrvatske),
                contentDescription = "Karta Hrvatske",
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Kategorije za: $region",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))

        categories.forEach { category ->
            Button(
                onClick = {
                    val encodedRegion = Uri.encode(region)
                    val encodedCategory = Uri.encode(category)

                    navController.navigate("locations/$encodedRegion/$encodedCategory")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8DEF8),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = category,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = { navController.navigate("my_list") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1976D2))
        ) {
            Icon(imageVector = Icons.Default.List, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("MOJA LISTA", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}