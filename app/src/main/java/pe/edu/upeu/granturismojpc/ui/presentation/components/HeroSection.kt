package pe.edu.upeu.granturismojpc.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.granturismojpc.R
import pe.edu.upeu.granturismojpc.ui.theme.LightOrangeColors
import kotlin.coroutines.coroutineContext

@Composable
fun HeroSection() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.person), // reemplaza con tu recurso
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = 300f
                ))
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Descubre Capachica",
                style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Vive una experiencia auténtica en el Lago Titicaca",
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Buscar asociaciones o servicios") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        shape = RoundedCornerShape(50),

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}
