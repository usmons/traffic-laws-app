package io.usmon.trafficlaws.core.extensions

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

// Created by Usmon Abdurakhmanv on 6/12/2022.

fun EditText.update(value: String?) {
    value?.let {
        if (value == this.text.toString()) return
        setText("")
        append(value)
    }
}

fun EditText.setOnTextChangeListener(listener : (String) -> Unit) {
    addTextChangedListener {
        listener(text.toString())
    }
}