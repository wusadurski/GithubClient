package wsadurski.com.githubclient.data.repositories.entity

import wsadurski.com.githubclient.domain.repositories.entity.Repository

data class RepositoryJson(
    val author: String,
    val name: String,
    val url: String,
    val description: String,
    val language: String,
    val stars: Int,
    val forks: Int,
    val currentPeriodStars: String
) {
    fun toRepository() = Repository(author, name, url, description, language, stars, forks, currentPeriodStars)
}