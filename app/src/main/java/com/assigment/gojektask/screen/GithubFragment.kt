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
import com.assigment.gojektask.room.RepoDBModel
import com.assigment.gojektask.utils.LiveDataWrapper
import kotlinx.android.synthetic.main.fragment_repository.*
import kotlinx.coroutines.Dispatchers


class GithubFragment : BaseFragment() {
    private val TAG = GithubFragment::class.java.simpleName
    private val mBaseViewModelFactory: BaseViewModelFactory = BaseViewModelFactory(Dispatchers.IO)
    lateinit var mRecyclerViewAdapter: RepositoryAdapter
    private val organizationName = "octokit"

    companion object {
        fun getInstance() = GithubFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_repository

    private val mViewModel: GitHubViewModel by lazy {
        ViewModelProvider(this, mBaseViewModelFactory)
            .get(GitHubViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecyclerView()

        //Start observing the targets
        mViewModel.repoLiveData.observe(viewLifecycleOwner, dataObserver)

        getTrendingRepository(false)

        swipeContainer.setOnRefreshListener {
            getTrendingRepository(true)
        }

        // handle retry action
        retryButton.setOnClickListener {
            getTrendingRepository(true)
        }

    }

    private fun setUpRecyclerView() {
        mRecyclerViewAdapter = RepositoryAdapter(arrayListOf())
        repoRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL
                )
        )
        repoRecyclerView.adapter = mRecyclerViewAdapter
    }

    private fun getTrendingRepository(isForceUpdate: Boolean) {
        mViewModel.checkForDataSource(organizationName, isForceUpdate)
    }

    //---------------Observers---------------//
    private val dataObserver = Observer<LiveDataWrapper<List<RepoDBModel>>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESULT.LOADING -> {
                // Loading data
                error_holder.visibility = View.GONE
                repoRecyclerView.visibility = View.VISIBLE
                repoRecyclerView.showShimmerAdapter()
            }
            LiveDataWrapper.RESULT.ERROR -> {
                // Error for http request
                repoRecyclerView.visibility = View.GONE
                repoRecyclerView.hideShimmerAdapter()
                error_holder.visibility = View.VISIBLE
                swipeContainer.isRefreshing = false
            }
            LiveDataWrapper.RESULT.SUCCESS -> {
                // Data from API
                error_holder.visibility = View.GONE
                repoRecyclerView.visibility = View.VISIBLE

                repoRecyclerView.hideShimmerAdapter()
                swipeContainer.isRefreshing = false

                val mainItemReceived = result.response as ArrayList<RepoDBModel>
                processData(mainItemReceived)
            }
        }
    }


    /**
     * Handle success data
     */
    private fun processData(listItems: ArrayList<RepoDBModel>) {
        Log.d(TAG, "Repo List Size ${listItems.size}")

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mRecyclerViewAdapter.updateListItems(listItems)
        }
    }


}