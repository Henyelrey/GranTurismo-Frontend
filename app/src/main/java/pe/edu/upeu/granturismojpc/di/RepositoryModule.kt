package pe.edu.upeu.granturismojpc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.granturismojpc.repository.UsuarioRepository
import pe.edu.upeu.granturismojpc.repository.UsuarioRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun userRepository(userRepos: UsuarioRepositoryImp): UsuarioRepository
}
