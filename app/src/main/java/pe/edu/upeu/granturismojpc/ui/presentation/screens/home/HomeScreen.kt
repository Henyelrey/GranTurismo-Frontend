package pe.edu.upeu.granturismojpc.ui.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.granturismojpc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.granturismojpc.ui.presentation.components.CategoryChips
import pe.edu.upeu.granturismojpc.ui.presentation.components.HeroSection
import pe.edu.upeu.granturismojpc.ui.presentation.components.TopBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.upeu.granturismojpc.ui.navigation.Destinations

@Composable
fun HomeScreen(
    navegarPantalla2: (String) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val openDialog = {
        // Aquí puedes implementar lógica de abrir filtros
        println("Abriendo diálogo de filtros...")
    }

    val displaySnackBar = {
        // Aquí puedes mostrar un SnackBar o cualquier feedback
        println("Mostrando snackbar...")
    }
    val searchQuery = remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = drawerState,
                openDialog = openDialog,
                displaySnackBar = displaySnackBar
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = listOf(
                    Destinations.Pantalla2,
                    Destinations.Pantalla3,
                    Destinations.Pantalla4
                )
            )


        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection()

            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                label = { Text("Buscar asociaciones o servicios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, end = 22.dp, start = 22.dp),
                singleLine = true
            )

            CategoryChips()
        }
    }
}