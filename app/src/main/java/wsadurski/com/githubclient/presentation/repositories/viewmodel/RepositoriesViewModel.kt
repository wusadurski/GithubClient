package wsadurski.com.githubclient.presentation.repositories.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper
import wsadurski.com.githubclient.domain.repositories.entity.Repository
import wsadurski.com.githubclient.domain.repositories.interactor.GetTrendingRepositories

class RepositoriesViewModel(private val getTrendingRepositories: GetTrendingRepositories) : ViewModel() {

    var repositoriesLiveData: MutableLiveData<List<Repository>> = MutableLiveData()
    var errorThrowableWrapperLiveData: MutableLiveData<ErrorThrowableWrapper> = MutableLiveData()

    fun loadRepositories() =
        getTrendingRepositories.execute { either ->
            either.mapLeft { errorThrowableWrapper: ErrorThrowableWrapper ->
                this.errorThrowableWrapperLiveData.value = errorThrowableWrapper
            }
            either.map { list: List<Repository> -> this.repositoriesLiveData.value = list }
        }
}