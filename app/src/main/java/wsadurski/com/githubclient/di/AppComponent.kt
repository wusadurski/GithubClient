package wsadurski.com.githubclient.di

import dagger.Component
import wsadurski.com.githubclient.data.di.DataModule
import wsadurski.com.githubclient.data.repositories.di.RepositoriesModule
import wsadurski.com.githubclient.presentation.repositories.RepositoriesActivity
import wsadurski.com.githubclient.presentation.repositories.viewmodel.RepositoriesViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, RepositoriesModule::class])
interface AppComponent {
    fun inject(repositoriesActivity: RepositoriesActivity)
    fun inject(repositoriesViewModelFactory: RepositoriesViewModelFactory)
}
