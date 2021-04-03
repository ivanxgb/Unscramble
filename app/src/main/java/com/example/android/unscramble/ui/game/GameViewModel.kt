package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    val score get() = _score

    private var _currentWordCount = 0
    val currentWordCount get() = _currentWordCount
    private lateinit var _currentScrambledWord: String
    val currentScrambledWord get() = _currentScrambledWord

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String


    init {
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()

        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(currentWord, false)){
            tempWord.shuffle()
        }

        if (wordList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(currentWord, false)){
            increaseScore()
            true
        } else {
            false
        }
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }




}