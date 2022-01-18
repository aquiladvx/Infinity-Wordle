package com.aquiladvx.infinitywordle.data.services

import com.aquiladvx.infinitywordle.core.constants.GET_NEW_RANDOM_WORD
import com.aquiladvx.infinitywordle.data.entities.RandomWordRequest
import com.aquiladvx.infinitywordle.data.entities.RandomWordResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
interface WordApi {

    @POST(GET_NEW_RANDOM_WORD)
    suspend fun getRandomWord(@Body request: RandomWordRequest): Response<RandomWordResponse>


}