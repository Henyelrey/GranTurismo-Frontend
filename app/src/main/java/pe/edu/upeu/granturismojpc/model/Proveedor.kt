package pe.edu.upeu.granturismojpc.model

data class Proveedor(
    val idProveedor: Long,
    val nombre: String,
    val ruc: String,
    val direccion: String,
    val telefono: String,
    val correo: String
)

