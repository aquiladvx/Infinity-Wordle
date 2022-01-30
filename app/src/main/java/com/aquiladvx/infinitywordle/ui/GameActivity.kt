package com.aquiladvx.infinitywordle.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.aquiladvx.infinitywordle.R
import com.aquiladvx.infinitywordle.core.base.BaseActivity
import com.aquiladvx.infinitywordle.core.utils.getNamedId
import com.aquiladvx.infinitywordle.core.utils.observe
import com.aquiladvx.infinitywordle.core.utils.removeAccents
import com.aquiladvx.infinitywordle.data.Resource
import com.aquiladvx.infinitywordle.data.repository.GameRepository
import com.aquiladvx.infinitywordle.databinding.ActivityGameBinding
import com.aquiladvx.infinitywordle.ui.viewmodel.GameViewModel
import java.util.*


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class GameActivity : BaseActivity(), View.OnClickListener {


    private var attempt = 1

    private val keyboardHelper by lazy {
        KeyboardHelper(this)
    }
    private val boardHelper by lazy {
        BoardHelper(this, binding)
    }


    private val viewModel: GameViewModel by viewModels {
        GameViewModel.GameViewModelFactory(GameRepository(this))
    }
    private lateinit var binding: ActivityGameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()
        setupUI()
        startNewGame()
    }


    private fun getNewWord() {
        viewModel.getNewWord()
    }

    private fun handleNewWord(resource: Resource<String>) {
        when (resource) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success -> {
                hideLoading()
            }
            is Resource.DataError -> {
                hideLoading()
            }
        }
    }

    private fun startNewGame() {
        keyboardHelper.resetKeyboard()
        boardHelper.resetBoard()
        attempt = 1
        getNewWord()
    }

    private fun setupUI() {
        keyboardHelper.setupKeyboard(binding)
        boardHelper.setupAttempt(attempt)
        setupListeners()
    }

    private fun setupListeners() {
        binding.clKeyboard.del.setOnClickListener(this)
        binding.clKeyboard.enter.setOnClickListener(this)

        keyboardHelper.keyboard.forEach {
            it.setOnClickListener(this)
        }
    }

    private fun setObservers() {
        observe(viewModel.selectedWord, ::handleNewWord)
    }

    override fun initViewBinding() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.selectedWord.value?.data?.isEmpty() == true) {
            getNewWord()
        }
    }

    override fun onClick(view: View) {
        val id = view.getNamedId()
        if (id.length == 1 && id.lowercase(Locale.getDefault()) in "a".."z") {
            boardHelper.updateCurrentWord(id)
        } else {
            when (id.lowercase(Locale.getDefault())) {

                "del" -> {
                    boardHelper.removeLastLetter()
                }
                "enter" -> {
                    makeGuess()
                }
                "no-id" -> {
                    //TODO: Show error
                }
            }
        }
    }

    private fun getCurrentWord(): String? {
        return viewModel.selectedWord.value?.data
    }

    private fun makeGuess() {
        val userWord = boardHelper.userWord
        if (userWord.length == 5) {

            if (attempt < 6) {
                if (userWord.lowercase(Locale.getDefault()) == getCurrentWord()?.removeAccents()) {
                    //TODO SUCESSO
                    paintLetters()
                    Toast.makeText(this, "sucesso", Toast.LENGTH_SHORT).show()
                    startNewGame()
                } else {
                    //TODO FAILURE
                    paintLetters()
                    boardHelper.userWord = ""
                    attempt++
                    boardHelper.setupAttempt(attempt)
                }
            } else {

                //TODO GAMEOVER
                paintLetters()
                Toast.makeText(this, "a palavra era ${getCurrentWord()}", Toast.LENGTH_SHORT).show()
                startNewGame()
            }


        } else {
            //TODO INVALID WORD
        }
    }

    private fun paintLetters() {
        val attemptRow = boardHelper.attemptRow
        for (indice in attemptRow.indices) {
            val letter = attemptRow[indice].first.text.toString()
            val background = attemptRow[indice].second
            val currentWord = getCurrentWord()
            currentWord?.let {
                if (it.contains(letter.lowercase(Locale.getDefault())) && letter.lowercase(Locale.getDefault()) != it[indice].toString()) {
                    //LETRA NA POSICAO ERRADA
                    background.background =
                        ContextCompat.getDrawable(this, R.drawable.letter_background_wrong_place)
                    keyboardHelper.paintKeyboard(
                        R.drawable.letter_background_wrong_place,
                        letter
                    )
                } else if (letter.lowercase(Locale.getDefault()) == it[indice].toString()
                ) {
                    background.background =
                        ContextCompat.getDrawable(this, R.drawable.letter_background_right_place)
                    keyboardHelper.paintKeyboard(
                        R.drawable.letter_background_right_place,
                        letter
                    )

                } else {
                    background.background =
                        ContextCompat.getDrawable(this, R.drawable.letter_background_wrong_letter)
                    keyboardHelper.paintKeyboard(
                        R.drawable.letter_background_wrong_letter,
                        letter
                    )

                }
            }

        }
    }


}