package wsadurski.com.githubclient.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_repository_details_activity.toolbar
import wsadurski.com.githubclient.databinding.ViewRepositoryDetailsActivityBinding
import wsadurski.com.githubclient.domain.repositories.entity.Repository

class RepositoryDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ViewRepositoryDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewRepositoryDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayRepositoryDetailsFromIntent()
    }

    private fun displayRepositoryDetailsFromIntent() {
        val repository = intent.getSerializableExtra(REPOSITORY_TO_SHOW_KEY) as Repository
        toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.repository = repository
    }

    companion object {
        const val REPOSITORY_TO_SHOW_KEY = "REPOSITORY_TO_SHOW"
    }
}