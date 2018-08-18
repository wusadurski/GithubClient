package wsadurski.com.githubclient.domain.repositories.interactor

import wsadurski.com.githubclient.domain.interactor.ArgumentLessUseCase
import wsadurski.com.githubclient.domain.repositories.entity.Repository
import wsadurski.com.githubclient.domain.repositories.repository.RepositoriesRepository

class GetTrendingRepositories(private val repositoriesRepository: RepositoriesRepository) :
    ArgumentLessUseCase<List<Repository>>() {

    override suspend fun run() = repositoriesRepository.getTrendingRepositories()
}
