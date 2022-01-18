package com.aquiladvx.infinitywordle.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }
}