package pe.edu.upeu.granturismojpc.model

data class Proveedor(
    var idProveedor: Long,
    var nombreCompleto: String,
    var email: String,
    var telefono: String,
    var fechaRegistro: String,
    var estado: String,
    var usuario: UsuarioDto
)

