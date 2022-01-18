package com.aquiladvx.infinitywordle.ui

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.aquiladvx.infinitywordle.R
import com.aquiladvx.infinitywordle.core.utils.getNamedId
import com.aquiladvx.infinitywordle.databinding.ActivityGameBinding


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class KeyboardHelper(val context: Context) {

    lateinit var keyboard: List<ConstraintLayout>

    fun setupKeyboard(binding: ActivityGameBinding) {
        keyboard =  listOf(
            binding.clKeyboard.a,
            binding.clKeyboard.b,
            binding.clKeyboard.c,
            binding.clKeyboard.d,
            binding.clKeyboard.e,
            binding.clKeyboard.f,
            binding.clKeyboard.g,
            binding.clKeyboard.h,
            binding.clKeyboard.i,
            binding.clKeyboard.j,
            binding.clKeyboard.k,
            binding.clKeyboard.l,
            binding.clKeyboard.m,
            binding.clKeyboard.n,
            binding.clKeyboard.o,
            binding.clKeyboard.p,
            binding.clKeyboard.q,
            binding.clKeyboard.r,
            binding.clKeyboard.s,
            binding.clKeyboard.t,
            binding.clKeyboard.u,
            binding.clKeyboard.v,
            binding.clKeyboard.w,
            binding.clKeyboard.x,
            binding.clKeyboard.y,
            binding.clKeyboard.z,
        )

    }

    private fun resetKeyboard() {
        keyboard.forEach {
            it.background = ContextCompat.getDrawable(context, R.drawable.letter_background)
        }
    }

    private fun paintKeyboard(keyboard: List<ConstraintLayout>, color: Int, letter: String) {
        val keyboardKey = keyboard.find { it.getNamedId() == letter }
        keyboardKey?.setBackgroundColor(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }

}