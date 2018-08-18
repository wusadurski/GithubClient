package wsadurski.com.githubclient.data.repositories.service

import retrofit2.Call
import retrofit2.http.GET
import wsadurski.com.githubclient.data.repositories.entity.RepositoryJson

interface RepositoriesService {
    @GET("repositories")
    fun getTrendingRepositories(): Call<List<RepositoryJson>>
}
