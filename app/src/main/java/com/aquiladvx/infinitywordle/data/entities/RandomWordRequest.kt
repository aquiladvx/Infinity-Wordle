package com.aquiladvx.infinitywordle.data.entities
import com.google.gson.annotations.SerializedName


data class RandomWordRequest(
    @SerializedName("DictionaryName")
    val dictionaryName: String,
    @SerializedName("Filter")
    val filter: Filter,
    @SerializedName("LanguageName")
    val languageName: String,
    @SerializedName("NumberOfWords")
    val numberOfWords: Int
)

data class Filter(
    @SerializedName("Contains")
    val contains: String,
    @SerializedName("EndsWith")
    val endsWith: String,
    @SerializedName("MaxNumberOfLetters")
    val maxNumberOfLetters: Int,
    @SerializedName("MinNumberOfLetters")
    val minNumberOfLetters: Int,
    @SerializedName("StartsWith")
    val startsWith: String
)