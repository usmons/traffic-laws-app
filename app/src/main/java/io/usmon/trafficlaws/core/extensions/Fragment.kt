package io.usmon.trafficlaws.core.extensions

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import io.usmon.trafficlaws.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

// Created by Usmon Abdurakhmanv on 6/11/2022.

fun <T> Fragment.collect(flow: StateFlow<T>, run: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(run)
        }
    }
}

fun <T> Fragment.collect(flow: Flow<T>, run: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(run)
        }
    }
}

fun <T> Fragment.sdk28orUp(body: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        body()
    } else null
}