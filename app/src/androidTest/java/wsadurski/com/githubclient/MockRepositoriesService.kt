package wsadurski.com.githubclient

import android.content.Context
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate
import wsadurski.com.githubclient.data.repositories.entity.TrendingRepositoriesResponseItem
import wsadurski.com.githubclient.data.repositories.service.RepositoriesService

class MockRepositoriesService(
    private val context: Context,
    private val delegate: BehaviorDelegate<RepositoriesService>
) :
    RepositoriesService {

    override fun getTrendingRepositories(): Call<List<TrendingRepositoriesResponseItem>> {
        val response = Gson().fromJson(
            getString(
                context,
                "mock_trending_repositories_response.json"
            ),
            Array<TrendingRepositoriesResponseItem>::class.java
        )
        return delegate.returningResponse(response.toList()).getTrendingRepositories()
    }

    private fun getString(context: Context, assetName: String): String {
        return context.assets.open(assetName).bufferedReader().use {
            it.readText()
        }
    }
}
