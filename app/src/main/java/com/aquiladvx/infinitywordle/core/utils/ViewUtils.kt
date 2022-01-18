package com.aquiladvx.infinitywordle.core.utils

import android.view.View
import java.util.*


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */

fun View.getNamedId(): String {
    return when {
        this.resources.getResourceName(this.id).contains("del") -> "del"
        this.resources.getResourceName(this.id).contains("enter") -> "enter"
        this.id == View.NO_ID -> "no-id"
        else -> this.resources.getResourceName(this.id).last().toString()
            .uppercase(Locale.getDefault())
    }
}