package wsadurski.com.githubclient.domain.repositories.repository

import arrow.core.Either
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper
import wsadurski.com.githubclient.domain.repositories.entity.Repository

interface RepositoriesRepository {
    fun getTrendingRepositories(): Either<ErrorThrowableWrapper, List<Repository>>
}
