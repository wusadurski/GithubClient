package wsadurski.com.githubclient.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import wsadurski.com.githubclient.databinding.ViewItemRepositoryBinding
import wsadurski.com.githubclient.domain.repositories.entity.Repository

typealias ItemClickListener = (Repository) -> Unit

class RepositoriesAdapter(
    private val repositoryItems: List<Repository>,
    private val listener: ItemClickListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateItem(repositoryItems[position])
    }

    override fun getItemCount(): Int {
        return repositoryItems.size
    }

    inner class ViewHolder(val binding: ViewItemRepositoryBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        var repositoryItem: Repository? = null

        fun populateItem(item: Repository) {
            repositoryItem = item
            binding.nameTextview.text = item.name
            Glide.with(binding.avatarImageview).load(item.avatar).into(binding.avatarImageview)
        }

        init {
            binding?.root?.setOnClickListener {
                repositoryItem?.let {
                    listener.invoke(it)
                }
            }
        }
    }
}