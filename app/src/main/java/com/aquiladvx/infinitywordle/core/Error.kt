package com.aquiladvx.infinitywordle.core

/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */

class Error(val code: Int, val description: String) {
    constructor(exception: Exception) : this(code = DEFAULT_ERROR, description = exception.message
        ?: "")
}

const val NO_INTERNET_CONNECTION = -1
const val NETWORK_ERROR = -2
const val DEFAULT_ERROR = -3
const val GET_WORD_ERROR = -4