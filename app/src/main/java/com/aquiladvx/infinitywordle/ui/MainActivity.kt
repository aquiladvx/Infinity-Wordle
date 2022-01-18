package com.aquiladvx.infinitywordle.ui

import android.content.Intent
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

    override fun onResume() {
        super.onResume()

        startGame()
    }

    private fun startGame() {
        val intent = Intent(this, GameActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }


}