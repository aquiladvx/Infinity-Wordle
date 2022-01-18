package com.aquiladvx.infinitywordle.data.entities
import com.google.gson.annotations.SerializedName




class RandomWordResponse : ArrayList<String>() {

    fun getRandomWord(): String {
        return this[0]
    }
}