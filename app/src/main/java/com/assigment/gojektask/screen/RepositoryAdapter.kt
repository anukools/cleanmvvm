package com.assigment.gojektask.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assigment.gojektask.R
import com.assigment.gojektask.model.GitHubRepoModel

class RepositoryAdapter (val context: Context, list: ArrayList<GitHubRepoModel.GitHubRepoModelItem>): RecyclerView.Adapter<RepositoryAdapter.DataViewHolder>() {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.repo_list_view_item,parent,false))
    }

    fun updateListItems(updatedList: ArrayList<GitHubRepoModel.GitHubRepoModelItem>){
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val model : GitHubRepoModel.GitHubRepoModelItem = mItemList[position]
        holder.personName.text = model.name
        holder.dob.text = model.description
    }

    class DataViewHolder(item: View): RecyclerView.ViewHolder(item){
        val personName : TextView = item.findViewById(R.id.personName)
        val dob : TextView = item.findViewById(R.id.dateOfBirth)
    }
}