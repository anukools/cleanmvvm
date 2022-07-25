package com.assigment.gojektask.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assigment.gojektask.base.BaseViewHolder
import com.assigment.gojektask.databinding.RepoListViewItemBinding
import com.assigment.gojektask.model.GitHubRepoModel

class RepositoryAdapter(list: ArrayList<GitHubRepoModel.GitHubRepoModelItem>) :
    RecyclerView.Adapter<RepositoryAdapter.DataViewHolder>() {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoListViewItemBinding.inflate(layoutInflater, parent, false)
        return DataViewHolder(binding)
    }

    fun updateListItems(updatedList: ArrayList<GitHubRepoModel.GitHubRepoModelItem>) {
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(position)
    }

    var mExpandedPosition = -1

    inner class DataViewHolder(private val binding: RepoListViewItemBinding) :
        BaseViewHolder(binding.root) {

        init {
            binding.parentLayout.setOnClickListener {
                val shouldExpand = binding.expandLayout.visibility == View.GONE
                if (shouldExpand) {
                    binding.expandLayout.visibility = View.VISIBLE
                    if (mExpandedPosition != -1 && mExpandedPosition != adapterPosition) {
                        notifyItemChanged(mExpandedPosition)
                    }
                    mExpandedPosition = adapterPosition
                } else {
                    binding.expandLayout.visibility = View.GONE
                }
            }
        }

        override fun onBind(position: Int) {
            val repoData: GitHubRepoModel.GitHubRepoModelItem = mItemList[position]
            binding.repoData = repoData
            // make sure to include this so your view will be updated
            binding.executePendingBindings()

            val shouldCollapse = binding.expandLayout.visibility == View.VISIBLE
            if (shouldCollapse) {
                binding.expandLayout.visibility = View.GONE
            }
        }

    }
}