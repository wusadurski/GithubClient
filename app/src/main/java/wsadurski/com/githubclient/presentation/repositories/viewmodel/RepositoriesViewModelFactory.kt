package wsadurski.com.githubclient.presentation.repositories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import wsadurski.com.githubclient.GithubClientApplication
import wsadurski.com.githubclient.domain.repositories.interactor.GetTrendingRepositories
import javax.inject.Inject

class RepositoriesViewModelFactory(applicationContext: Context) : ViewModelProvider.Factory {

    @Inject
    lateinit var getTrendingRepositories: GetTrendingRepositories

    init {
        (applicationContext as GithubClientApplication).appComponent.inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(getTrendingRepositories) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}