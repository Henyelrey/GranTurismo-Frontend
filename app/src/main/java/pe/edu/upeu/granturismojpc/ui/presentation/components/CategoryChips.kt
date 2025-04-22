package pe.edu.upeu.granturismojpc.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryChips() {
    val categories =
        listOf("Destacado", "Hospedaje", "Tours", "Artesanía", "Transporte", "Almuerzo")
    val icons = listOf(
        Icons.Default.Star,
        Icons.Default.Apartment,
        Icons.Default.Place,
        Icons.Default.ShoppingCart,
        Icons.Default.DirectionsCar,
        Icons.Default.Restaurant
    )

    LazyRow (
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.size) { index ->
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFFFF3E0),
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = categories[index],
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Text(categories[index], fontSize = 12.sp)
            }
        }
    }
}
