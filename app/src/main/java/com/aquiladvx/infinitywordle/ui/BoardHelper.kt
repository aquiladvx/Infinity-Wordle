package com.aquiladvx.infinitywordle.ui

import android.content.Context
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aquiladvx.infinitywordle.databinding.ActivityGameBinding
import com.aquiladvx.infinitywordle.databinding.WordBinding


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class BoardHelper(val context: Context, val binding: ActivityGameBinding) {

    private lateinit var attemptRow: List<Pair<TextView, ConstraintLayout>>
    var userWord = ""

    private fun setRow(row: WordBinding) {
        attemptRow = listOf(
            Pair(row.tvLetter1.tvLetter, row.tvLetter1.clLetter),
            Pair(row.tvLetter2.tvLetter, row.tvLetter2.clLetter),
            Pair(row.tvLetter3.tvLetter, row.tvLetter3.clLetter),
            Pair(row.tvLetter4.tvLetter, row.tvLetter4.clLetter),
            Pair(row.tvLetter5.tvLetter, row.tvLetter5.clLetter),
        )

        //TODO: Update background color?
    }

    fun setupAttempt(attempt: Int) {
        when (attempt) {
            1 -> setRow(binding.llGameBoard.attempt1)
            2 -> setRow(binding.llGameBoard.attempt2)
            3 -> setRow(binding.llGameBoard.attempt3)
            4 -> setRow(binding.llGameBoard.attempt4)
            5 -> setRow(binding.llGameBoard.attempt5)
            6 -> setRow(binding.llGameBoard.attempt6)
        }
    }

    fun updateCurrentWord(id: String) {
        if (userWord.length < 5) {
            userWord += id
            updateRow()
        }
    }

    private fun updateRow() {
        clearRow()
        if (userWord.isNotEmpty()) {
            for (index in userWord.indices) {
                attemptRow[index].first.text = userWord[index].toString()
            }

        }
    }

    private fun clearRow() {
        for (index in attemptRow.indices) {
            attemptRow[index].first.text = ""
        }
    }

    fun removeLastLetter() {
        userWord = userWord.dropLast(1)
        updateRow()
    }
}