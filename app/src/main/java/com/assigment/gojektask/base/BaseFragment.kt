package com.assigment.gojektask.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

abstract class BaseFragment : Fragment() {

    // OVERRIDE ---
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    // ABSTRACT ---
    abstract fun getLayoutId(): Int

    private var mCallBackAlertDialog: AlertDialog? = null


    fun showToast(msg: String) {
        requireActivity().runOnUiThread {
            val toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    fun loadImageURL(context: Context, imageView: ImageView, imageURL: String) {
        Glide.with(context).load(imageURL)
            .fallback(android.R.drawable.stat_notify_error)
            .timeout(4500)
            .into(imageView)
    }
}