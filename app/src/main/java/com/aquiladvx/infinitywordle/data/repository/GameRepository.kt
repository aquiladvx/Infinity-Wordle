package com.aquiladvx.infinitywordle.data.repository

import android.content.Context
import com.aquiladvx.infinitywordle.core.constants.URL_WORDS
import com.aquiladvx.infinitywordle.data.entities.RandomWordRequest
import com.aquiladvx.infinitywordle.data.entities.RandomWordResponse
import com.aquiladvx.infinitywordle.data.services.WordApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class GameRepository(context: Context) {


    suspend fun getNewWord(newWordRequestBody: RandomWordRequest): Flow<Response<RandomWordResponse>> {
        return flow {
            val retrofit = getRetrofit(GsonConverterFactory.create())
            val service = retrofit.create(WordApi::class.java)
            emit(service.getRandomWord(newWordRequestBody))
        }.flowOn(Dispatchers.IO)
    }

    private fun getRetrofit(factory: Converter.Factory? = null): Retrofit {
        return Retrofit.Builder().also {
            it.baseUrl(URL_WORDS)
            factory?.let { factory ->
                it.addConverterFactory(factory)
            }

        }.build()
    }
}