package com.aquiladvx.infinitywordle.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aquiladvx.infinitywordle.ui.Loader


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
abstract class BaseActivity: AppCompatActivity() {
    var isLoading = false
    lateinit var loader: Loader

    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }

    fun showLoading() {
        if(!isLoading) {
            loader = Loader()
            loader.show(supportFragmentManager, "loading")
            isLoading = true
        }
    }

    fun hideLoading() {
        if (isLoading) {
            loader.dismiss()
            isLoading = false
        }
    }
}