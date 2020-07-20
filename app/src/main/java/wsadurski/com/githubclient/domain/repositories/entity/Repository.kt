package wsadurski.com.githubclient.domain.repositories.entity

import java.io.Serializable

data class Repository(
    val author: String,
    val name: String,
    val url: String,
    val description: String,
    val language: String?,
    val stars: Int,
    val forks: Int,
    val currentPeriodStars: Int
) : Serializable