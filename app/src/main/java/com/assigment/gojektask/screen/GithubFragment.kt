package com.assigment.gojektask.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assigment.gojektask.R
import com.assigment.gojektask.base.BaseFragment
import com.assigment.gojektask.base.BaseViewModelFactory
import com.assigment.gojektask.model.GitHubRepoModel
import com.assigment.gojektask.utils.LiveDataWrapper
import kotlinx.android.synthetic.main.fragment_repository.*
import kotlinx.coroutines.Dispatchers


class GithubFragment : BaseFragment() {
    private val TAG = GithubFragment::class.java.simpleName

    companion object {
        fun getInstance() = GithubFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_repository

    private val mBaseViewModelFactory : BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO)

    val mDemoParam = "octokit"
    lateinit var mRecyclerViewAdapter: RepositoryAdapter

    private val mViewModel : GitHubViewModel by lazy {
        ViewModelProvider(this, mBaseViewModelFactory)
            .get(GitHubViewModel::class.java)    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Start observing the targets
        this.mViewModel.repoLiveData.observe(viewLifecycleOwner, this.mDataObserver)

        mRecyclerViewAdapter = RepositoryAdapter(arrayListOf())
        repoRecyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
        repoRecyclerView.adapter = mRecyclerViewAdapter

        getRepoAPI()

        retryButton.setOnClickListener {
            getRepoAPI()
        }

    }

    fun getRepoAPI(){
        mViewModel.getRepositoryData(mDemoParam)
    }

    //---------------Observers---------------//
    private val mDataObserver = Observer<LiveDataWrapper<GitHubRepoModel>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESULT.LOADING -> {
                // Loading data
                progress_circular.visibility = View.VISIBLE
                error_holder.visibility = View.GONE
                repoRecyclerView.visibility = View.GONE
            }
            LiveDataWrapper.RESULT.ERROR -> {
                // Error for http request
                progress_circular.visibility = View.GONE
                repoRecyclerView.visibility = View.GONE
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")

            }
            LiveDataWrapper.RESULT.SUCCESS -> {
                // Data from API
                progress_circular.visibility = View.GONE
                error_holder.visibility = View.GONE

                repoRecyclerView.visibility = View.VISIBLE
                val mainItemReceived = result.response as GitHubRepoModel
                processData(mainItemReceived)
            }
        }
    }


    /**
     * Handle success data
     */
    private fun processData(listItems: GitHubRepoModel) {
        Log.d(TAG, "processData called with data ${listItems.size}")
        Log.d(TAG, "processData listItems =  ${listItems}")

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mRecyclerViewAdapter.updateListItems(listItems)
        }
    }


}