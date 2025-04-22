package pe.edu.upeu.granturismojpc.data.remote

import pe.edu.upeu.granturismojpc.model.Proveedor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RestProveedor {
    companion object {
        const val BASE_RUTA = "/proveedores"
    }
    @GET("${BASE_RUTA}")
    suspend fun reportarProveedores(@Header("Authorization")
                                   token:String): Response<List<Proveedor>>
}