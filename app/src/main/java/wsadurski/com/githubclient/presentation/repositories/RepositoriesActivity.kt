package wsadurski.com.githubclient.presentation.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.view_repositories_activity.repositories_recycler
import wsadurski.com.githubclient.R
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper
import wsadurski.com.githubclient.domain.repositories.entity.Repository
import wsadurski.com.githubclient.presentation.details.RepositoryDetailsActivity
import wsadurski.com.githubclient.presentation.repositories.viewmodel.RepositoriesViewModel
import wsadurski.com.githubclient.presentation.repositories.viewmodel.RepositoriesViewModelFactory

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var repositoriesViewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_repositories_activity)
        initView()
        initViewModel()
    }

    private fun initView() {
        repositories_recycler.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider_grey)?.let {
            divider.setDrawable(it)
        }
        repositories_recycler.addItemDecoration(divider)
        title = getString(R.string.trending_github_repositories)
    }

    private fun initViewModel() {
        repositoriesViewModel =
            ViewModelProviders.of(this, RepositoriesViewModelFactory(this.applicationContext))
                .get(RepositoriesViewModel::class.java)

        repositoriesViewModel.repositoriesLiveData.observe(this, Observer {
            repositories_recycler.adapter =
                RepositoriesAdapter(it!!) { repository -> onRepositoryItemClick(repository) }
        })

        repositoriesViewModel.errorThrowableWrapperLiveData.observe(this, Observer {
            onError(it!!)
        })
        repositoriesViewModel.loadRepositories()
    }

    private fun onRepositoryItemClick(repository: Repository) {
        val intent = Intent(this, RepositoryDetailsActivity::class.java)
        intent.putExtra(RepositoryDetailsActivity.REPOSITORY_TO_SHOW_KEY, repository)
        startActivity(intent)
    }

    private fun onError(errorThrowableWrapper: ErrorThrowableWrapper) {
        Toast.makeText(this, errorThrowableWrapper.toString(), Toast.LENGTH_SHORT).show()
    }
}
