package com.aquiladvx.infinitywordle.core.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}