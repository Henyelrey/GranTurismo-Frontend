package pe.edu.upeu.granturismojpc.ui.presentation.screens.paquete

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upeu.granturismojpc.model.PaqueteCreateDto
import pe.edu.upeu.granturismojpc.model.PaqueteDto
import pe.edu.upeu.granturismojpc.model.PaqueteResp
import pe.edu.upeu.granturismojpc.model.Proveedor
import pe.edu.upeu.granturismojpc.repository.PaqueteRepository
import pe.edu.upeu.granturismojpc.repository.ProveedorRepository
import javax.inject.Inject

@HiltViewModel
class PaqueteFormViewModel @Inject constructor(
    private val packRepo: PaqueteRepository,
    private val provRepo: ProveedorRepository,
    /*private val cateRepo: CategoriaRepository,
    private val umRepo: UnidadMedidaRepository,*/
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _paquete = MutableStateFlow<PaqueteResp?>(null)
    val paquete: StateFlow<PaqueteResp?> = _paquete

    private val _provs = MutableStateFlow<List<Proveedor>>(emptyList())
    val provs: StateFlow<List<Proveedor>> = _provs

    /*private val _categors = MutableStateFlow<List<Categoria>>(emptyList())
    val categors: StateFlow<List<Categoria>> = _categors

    private val _unidMeds = MutableStateFlow<List<UnidadMedida>>(emptyList())
    val unidMeds: StateFlow<List<UnidadMedida>> = _unidMeds*/

    fun getPaquete(idX: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _paquete.value = packRepo.buscarPaqueteId(idX)
            _isLoading.value = false
        }
    }

    fun getDatosPrevios() {
        viewModelScope.launch {
            _provs.value = provRepo.findAll()
            //_categors.value = cateRepo.findAll()
            //_unidMeds.value = umRepo.findAll()
        }
    }

    fun addPaquete(paquete: PaqueteDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true

            // Convertir PaqueteDto a PaqueteCreateDto para excluir el idPaquete
            val paqueteCreateDto = PaqueteCreateDto(
                titulo = paquete.titulo,
                descripcion = paquete.descripcion,
                precio = paquete.precio,
                imagenUrl = paquete.imagenUrl,
                localidad = paquete.localidad,
                tipoActividad = paquete.tipoActividad,
                cuposMaximos = paquete.cuposMaximos,
                proveedor = paquete.proveedor,
                fechaInicio = paquete.fechaInicio,
                fechaFin = paquete.fechaFin
            )

            Log.i("REAL", "Creando paquete: $paqueteCreateDto")
            packRepo.insertarPaquete(paqueteCreateDto)
            _isLoading.value = false
        }
    }

    fun editPaquete(paquete: PaqueteDto){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            packRepo.modificarPaquete(paquete)
            _isLoading.value = false
        }
    }
}