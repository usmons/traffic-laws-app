package io.usmon.trafficlaws.core.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import io.usmon.trafficlaws.domain.model.Category

// Created by Usmon Abdurakhmanv on 6/12/2022.

inline fun <reified T> Spinner.onItemSelected(crossinline selected: (T?) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selected(selectedItem as? T)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
}

fun Spinner.updateCategory(category: Category?) {
    category?.let {
        setSelection(Category.getPosition(category))
    }
}