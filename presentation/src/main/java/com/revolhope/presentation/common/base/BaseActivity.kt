package com.revolhope.presentation.common.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.revolhope.presentation.R

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    enum class Navigation {
        MODAL,
        LATERAL
    }

    companion object {
        const val EXTRA_NAVIGATION = "extra.base.navigation"
    }

    abstract val binding: T

    private var isFirstOnResume = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onBindViews()
        onStartObservers()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResume) {
            onLoadData()
            isFirstOnResume = false
        } else {
            onReloadData()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        checkNavigationTransition(intent, isBack = true)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        checkNavigationTransition(intent, isBack = false)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            //onBackPressed()
            //true
            super.onOptionsItemSelected(item)
        } else {
            super.onOptionsItemSelected(item)
        }*/

    open fun onBindViews() {
        // Nothing to do here
    }

    open fun onStartObservers() {
        // Nothing to do here
    }

    open fun onLoadData() {
        // Nothing to do here
    }

    open fun onReloadData() {
        // Nothing to do here
    }

    private fun checkNavigationTransition(intent: Intent?, isBack: Boolean) {
        intent?.takeIf { it.hasExtra(EXTRA_NAVIGATION) }?.let {
            overrideTransition(
                navigation = it.extras!!.getSerializable(EXTRA_NAVIGATION) as Navigation,
                isBack = isBack
            )
        }
    }

    private fun overrideTransition(navigation: Navigation, isBack: Boolean) =
        when (navigation) {
            Navigation.MODAL -> {
                if (isBack) {
                    android.R.anim.fade_in to R.anim.slide_out_down
                } else {
                    R.anim.slide_in_up to android.R.anim.fade_out
                }
            }
            Navigation.LATERAL -> {
                if (isBack) {
                    R.anim.slide_in_left to R.anim.slide_out_right
                } else {
                    R.anim.slide_in_right to R.anim.slide_out_left
                }
            }
        }.run {
            overridePendingTransition(first, second)
        }
}