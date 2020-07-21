package wsadurski.com.githubclient

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import wsadurski.com.githubclient.data.repositories.di.RepositoriesModule
import wsadurski.com.githubclient.data.repositories.service.RepositoriesService
import javax.inject.Singleton

@Module
class MockRepositoriesModule(private val context: Context) : RepositoriesModule() {

    @Provides
    @Singleton
    override fun provideRepositoriesService(retrofit: Retrofit): RepositoriesService {
        val mockRetrofit = MockRetrofit.Builder(retrofit).build()
        val delegate = mockRetrofit.create(RepositoriesService::class.java)
        return MockRepositoriesService(context, delegate)
    }
}