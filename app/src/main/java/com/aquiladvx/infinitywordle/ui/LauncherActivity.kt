package com.aquiladvx.infinitywordle.ui

import android.content.Intent
import android.os.Bundle
import com.aquiladvx.infinitywordle.core.base.BaseActivity
import com.aquiladvx.infinitywordle.core.constants.SPLASH_SCREEN_ANIMATION_DURATION
import com.aquiladvx.infinitywordle.databinding.ActivityLauncherBinding

class LauncherActivity : BaseActivity() {

    private lateinit var binding: ActivityLauncherBinding

    override fun initViewBinding() {
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startSplashScreen()
    }

    private fun startSplashScreen() {
        binding.ivSplashscreen.alpha = 0f
        binding.ivSplashscreen.animate().setDuration(SPLASH_SCREEN_ANIMATION_DURATION).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}