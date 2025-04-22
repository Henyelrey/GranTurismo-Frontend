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
import pe.edu.upeu.granturismojpc.model.PaqueteDto
import pe.edu.upeu.granturismojpc.model.PaqueteResp
import pe.edu.upeu.granturismojpc.repository.PaqueteRepository
import javax.inject.Inject

@HiltViewModel
class PaqueteFormViewModel @Inject constructor(
    private val packRepo: PaqueteRepository,
    /*private val marcRepo: MarcaRepository,
    private val cateRepo: CategoriaRepository,
    private val umRepo: UnidadMedidaRepository,*/
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _paquete = MutableStateFlow<PaqueteResp?>(null)
    val paquete: StateFlow<PaqueteResp?> = _paquete

    /*private val _marcs = MutableStateFlow<List<Marca>>(emptyList())
    val marcs: StateFlow<List<Marca>> = _marcs

    private val _categors = MutableStateFlow<List<Categoria>>(emptyList())
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

    /*fun getDatosPrevios() {
        viewModelScope.launch {
            _marcs.value = marcRepo.findAll()
            _categors.value = cateRepo.findAll()
            _unidMeds.value = umRepo.findAll()
        }
    }*/

    fun addPaquete(paquete: PaqueteDto){
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            Log.i("REAL", paquete.toString())
            packRepo.insertarPaquete(paquete)
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