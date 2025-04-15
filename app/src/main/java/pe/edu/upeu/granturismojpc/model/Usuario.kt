package pe.edu.upeu.granturismojpc.model

data class UsuarioDto(
    var user: String,
    var clave: String,
)
data class UsuarioResp(
    val idUsuario: Long,
    val user: String,
    val token: String,
)