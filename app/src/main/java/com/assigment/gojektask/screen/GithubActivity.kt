package com.assigment.gojektask.screen

import android.os.Bundle
import android.view.Menu
import com.assigment.gojektask.R
import com.assigment.gojektask.base.BaseActivity

class GithubActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureAndShowFragment()
    }

    private fun configureAndShowFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.base_frame_layout) as GithubActivity?
        if(fragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.base_frame_layout, GithubFragment.getInstance())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}