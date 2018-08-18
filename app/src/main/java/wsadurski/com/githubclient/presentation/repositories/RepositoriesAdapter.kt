package wsadurski.com.githubclient.presentation.repositories

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wsadurski.com.githubclient.BR
import wsadurski.com.githubclient.databinding.ViewItemRepositoryBinding
import wsadurski.com.githubclient.domain.repositories.entity.Repository

typealias ItemClickListener = (Repository) -> Unit

class RepositoriesAdapter(private val repositoryItems: List<Repository>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateItem(repositoryItems[position])
    }

    override fun getItemCount(): Int {
        return repositoryItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var repositoryItem: Repository? = null
        val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)

        fun populateItem(item: Repository) {
            repositoryItem = item
            repositoryItem?.let {
                binding?.setVariable(BR.name, it.name)
            }
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