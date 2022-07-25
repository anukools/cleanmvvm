package com.assigment.gojektask.utils

import android.content.Context
import android.content.SharedPreferences
import com.assigment.gojektask.AppConstants

class SharePreferenceHelper(context: Context) {

    private val mSharedPrefKey = AppConstants.DS_SHARED_PREF_GITHUB
    private val mSharedPref : SharedPreferences = context.getSharedPreferences(mSharedPrefKey, Context.MODE_PRIVATE)
    private val  key : String = "LAST_FETCH_TIME"

    private val mSharedPrefEditor : SharedPreferences.Editor = mSharedPref.edit()

    fun setLastFetchTime(value: Long){
        mSharedPrefEditor.putLong(key, value)
        mSharedPrefEditor.commit()
    }

    fun getLastFetchTime(): Long = if (mSharedPref.contains(key))
        mSharedPref.getLong(key, 0L)
    else
        0L
}