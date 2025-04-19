package pe.edu.upeu.granturismojpc.repository

import pe.edu.upeu.granturismojpc.data.remote.RestUsuario
import pe.edu.upeu.granturismojpc.model.UsuarioDto
import pe.edu.upeu.granturismojpc.model.UsuarioRegisterDto
import pe.edu.upeu.granturismojpc.model.UsuarioResp
import retrofit2.Response
import javax.inject.Inject

interface UsuarioRepository {
    suspend fun loginUsuario(user: UsuarioDto):
            Response<UsuarioResp>
    suspend fun registerUsuario(user: UsuarioRegisterDto):
            Response<UsuarioResp>
}
class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario):UsuarioRepository{
    override suspend fun loginUsuario(user:UsuarioDto):
            Response<UsuarioResp>{
        return restUsuario.login(user)
    }
    override suspend fun registerUsuario(user: UsuarioRegisterDto):
            Response<UsuarioResp> {
        return restUsuario.register(user)
    }
}