package pe.edu.upeu.granturismojpc.repository

import pe.edu.upeu.granturismojpc.data.remote.RestProveedor
import pe.edu.upeu.granturismojpc.model.Proveedor
import pe.edu.upeu.granturismojpc.utils.TokenUtils
import javax.inject.Inject

interface ProveedorRepository {
    suspend fun findAll(): List<Proveedor>
}
class ProveedorRepositoryImp @Inject constructor(
    private val rest: RestProveedor,
): ProveedorRepository{
    override suspend fun findAll(): List<Proveedor> {
        val response =
            rest.reportarProveedores(TokenUtils.TOKEN_CONTENT)
        return if (response.isSuccessful) response.body() ?:
        emptyList()
        else emptyList()
    }
}
