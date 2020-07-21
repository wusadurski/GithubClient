package wsadurski.com.githubclient.presentation.repositories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.view_repositories_activity.*
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
        repositories_recycler.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(this, 2)
        val divider = androidx.recyclerview.widget.DividerItemDecoration(
            this,
            androidx.recyclerview.widget.RecyclerView.VERTICAL
        )
        ContextCompat.getDrawable(this, R.drawable.divider_grey)?.let {
            divider.setDrawable(it)
        }
        repositories_recycler.addItemDecoration(divider)
        title = getString(R.string.trending_github_repositories)
    }

    private fun initViewModel() {
        repositoriesViewModel =
            ViewModelProvider(this, RepositoriesViewModelFactory(this.applicationContext))
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
