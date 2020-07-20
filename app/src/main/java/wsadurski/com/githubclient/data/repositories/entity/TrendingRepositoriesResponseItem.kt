package wsadurski.com.githubclient.data.repositories.entity

import wsadurski.com.githubclient.domain.repositories.entity.Repository

data class TrendingRepositoriesResponseItem(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltBy>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String?,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
) {
    fun toRepository() = Repository(author, name, url, description, language, stars, forks, currentPeriodStars)
}