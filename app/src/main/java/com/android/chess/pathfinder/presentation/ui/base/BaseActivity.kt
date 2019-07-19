package com.android.chess.pathfinder.presentation.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Dimitris Konomis (konomis.dimitris@gmail.com) on 2019-07-18.
 **/

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayout() != 0) setContentView(getLayout())
    }

    abstract fun getLayout(): Int

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    fun showLoading() {
        if (progressDialog == null) progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    fun showLoading(message: String) {
        if (progressDialog == null) progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(message)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    fun dismissLoading() {
        progressDialog?.let { if (it.isShowing) it.dismiss() }
    }

}