package wsadurski.com.githubclient.data.repositories

import arrow.core.Either
import wsadurski.com.githubclient.data.repositories.service.RepositoriesService
import wsadurski.com.githubclient.data.util.SafeRepository
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper
import wsadurski.com.githubclient.domain.repositories.entity.Repository
import wsadurski.com.githubclient.domain.repositories.repository.RepositoriesRepository

class RepositoriesRepositoryImpl(
    private val service: RepositoriesService
) : RepositoriesRepository, SafeRepository() {

    override fun getTrendingRepositories(): Either<ErrorThrowableWrapper, List<Repository>> {
        return executeSafely(service.getTrendingRepositories(), { it.map { it.toRepository() } }, emptyList())
    }
}