package com.assigment.gojektask.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assigment.gojektask.screen.GitHubViewModel

/**
 * Base VM Factory for creation of different VM's
 */
class BaseViewModelFactory constructor() :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            GitHubViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}