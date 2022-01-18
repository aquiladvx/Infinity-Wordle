package com.aquiladvx.infinitywordle.ui

import android.os.Bundle
import com.aquiladvx.infinitywordle.core.base.BaseActivity
import com.aquiladvx.infinitywordle.databinding.ActivityMainBinding


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class MainActivity: BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
    }

    private fun setupUI() {

    }

}