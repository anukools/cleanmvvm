package com.assigment.gojektask.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assigment.gojektask.screen.GitHubViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Base VM Factory for creation of different VM's
 */
class BaseViewModelFactory constructor( private val mainDispather: CoroutineDispatcher
                                        ,private val ioDispatcher: CoroutineDispatcher
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            GitHubViewModel(mainDispather, ioDispatcher) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}