package com.assigment.gojektask.screen

import com.assigment.gojektask.R
import com.assigment.gojektask.base.BaseFragment

class GithubFragment : BaseFragment() {

    companion object {
        fun getInstance() = GithubFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_repository

}