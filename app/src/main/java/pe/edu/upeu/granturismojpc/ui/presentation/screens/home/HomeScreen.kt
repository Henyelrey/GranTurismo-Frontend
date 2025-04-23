package pe.edu.upeu.granturismojpc.ui.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.granturismojpc.ui.presentation.components.BottomNavigationBar

import pe.edu.upeu.granturismojpc.ui.presentation.components.HeroSection
import pe.edu.upeu.granturismojpc.ui.presentation.components.TopBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import pe.edu.upeu.granturismojpc.R
import pe.edu.upeu.granturismojpc.model.PaqueteResp
import pe.edu.upeu.granturismojpc.ui.navigation.Destinations
import pe.edu.upeu.granturismojpc.ui.presentation.components.LoadingCard
import pe.edu.upeu.granturismojpc.ui.presentation.screens.paquete.PaqueteGestion
import pe.edu.upeu.granturismojpc.ui.presentation.screens.paquete.PaqueteMainViewModel

@Composable
fun HomeScreen(
    navegarPantalla2: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,

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
    val paquetes by viewModel.prods.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarPaquetes()
    }



            Scaffold (
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
            modifier = Modifier.padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection(
                searchQuery = searchQuery.value,
                onSearchChange = { searchQuery.value = it })

            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                label = { Text("Buscar asociaciones ") },
                shape = RoundedCornerShape(50),
                modifier = Modifier


                    .fillMaxWidth()
                    .padding(top = 0.dp, end = 10.dp, start = 10.dp),
                singleLine = true
            )
            //Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Asociaciones Turisticas",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)
            )


        }
                /*PaqueteGestion(
                    navController = navController,
                    paquetes = paquetes,
                    isLoading = isLoading,
                    searchQuery = searchQuery,


                    )

                 */
    }




}
/*
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PaqueteGestion(
    navController: NavHostController,
    paquetes: List<PaqueteResp>,
    isLoading: Boolean,
    searchQuery: MutableState<String>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current


    var paquetesFiltrados = remember { mutableStateOf<List<PaqueteResp>>(emptyList()) }

    Box(modifier = Modifier.fillMaxSize().padding(top = 60.dp)) {
        paquetesFiltrados.value = paquetes.filter {
            it.titulo.contains(searchQuery.value, ignoreCase = true) ||
                    it.proveedor.nombreCompleto.contains(searchQuery.value, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                .align(Alignment.TopCenter)
        ) {
            val itemCount = paquetesFiltrados.value.size
            items(count = itemCount) { index ->
                val paquetex = paquetesFiltrados.value[index]
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data(paquetex.proveedor.nombreCompleto)
                                    .placeholder(R.drawable.bg)
                                    .error(R.drawable.bg)
                                    .build()
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                        pe.edu.upeu.granturismojpc.ui.presentation.components.Spacer()
                        Column(Modifier.weight(1f)) {
                            Text("${paquetex.titulo} - ${paquetex.precio}", fontWeight = FontWeight.Bold)
                            Text("${paquetex.proveedor.nombreCompleto} - ${paquetex.descripcion}",
                                color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
*/
