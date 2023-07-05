package com.example.githubprofilebrowser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofilebrowser.models.GithubReposResponseItem
import com.example.githubprofilebrowser.databinding.ItemViewBinding


class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.GithubReposViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<GithubReposResponseItem>(){
       override fun areItemsTheSame(oldItem: GithubReposResponseItem, newItem: GithubReposResponseItem): Boolean {
           return oldItem.name == newItem.name
       }

       override fun areContentsTheSame(oldItem: GithubReposResponseItem, newItem: GithubReposResponseItem): Boolean {
           return oldItem.equals(newItem)
       }

   }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubReposViewHolder {
        return GithubReposViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false) )
    }

    override fun onBindViewHolder(holder: GithubReposViewHolder, position: Int) {
        val repo = differ.currentList[position]
        with(holder){
            with(repo){
                binding.repoName.text = this.name
                binding.repoDesc.text = this.description
//                binding..text = this.name
//                binding.tvExp.text = this.exp
            }
        }
        //TODO
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner  class GithubReposViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)
//    {
//        fun bind(githubReposViewModel: GithubReposViewModel){
//            binding.repoName = githubReposViewModel.
//            binding.re = githubReposViewModel.desc
////            binding.layout.setOnClickListener
//            }
//        }

    private var onItemClickListener: ((GithubReposResponseItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (GithubReposResponseItem) -> Unit){
        onItemClickListener = listener
    }

}