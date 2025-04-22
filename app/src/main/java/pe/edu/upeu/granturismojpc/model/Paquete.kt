package pe.edu.upeu.granturismojpc.model

import java.math.BigDecimal
import java.time.LocalDate

data class PaqueteDto(
    var idPaquete: Long,
    var titulo: String,
    var descripcion: String,
    var precio: Double,
    var imagenUrl: String,
    var localidad: String,
    var tipoActividad: String,
    var cuposMaximos: Int,
    var proveedorId: Long,
    var fechaInicio: String,
    var fechaFin: String
)

data class PaqueteResp(
    val idPaquete: Long,
    val titulo: String,
    val descripcion: String,
    val precio: BigDecimal,
    val imagenUrl: String,
    val localidad: String,
    val tipoActividad: String,
    val cuposMaximos: Int,
    val proveedor: Proveedor,
    val fechaInicio: String,
    val fechaFin: String
)

fun PaqueteResp.toDto(): PaqueteDto {
    return PaqueteDto(
        idPaquete = this.idPaquete,
        titulo = this.titulo,
        descripcion = this.descripcion,
        precio = this.precio.toDouble(),
        imagenUrl = this.imagenUrl,
        localidad = this.localidad,
        tipoActividad = this.tipoActividad,
        cuposMaximos = this.cuposMaximos,
        proveedorId = this.proveedor.idProveedor,
        fechaInicio = this.fechaInicio,
        fechaFin = this.fechaFin
    )
}
