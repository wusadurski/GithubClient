package wsadurski.com.githubclient.data.repositories.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import wsadurski.com.githubclient.data.repositories.RepositoriesRepositoryImpl
import wsadurski.com.githubclient.data.repositories.service.RepositoriesService
import wsadurski.com.githubclient.domain.repositories.interactor.GetTrendingRepositories
import wsadurski.com.githubclient.domain.repositories.repository.RepositoriesRepository
import javax.inject.Singleton

@Module
open class RepositoriesModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        repositoriesService: RepositoriesService
    ): RepositoriesRepository = RepositoriesRepositoryImpl(repositoriesService)

    @Provides
    @Singleton
    open fun provideRepositoriesService(retrofit: Retrofit): RepositoriesService {
        return retrofit.create(RepositoriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetTrendingRepositoriesUseCase(repositoriesRepository: RepositoriesRepository) =
        GetTrendingRepositories(repositoriesRepository)
}