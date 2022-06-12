package io.usmon.trafficlaws.presentation.add_edit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import io.usmon.trafficlaws.databinding.SpinnerItemDropdownBinding
import io.usmon.trafficlaws.domain.model.Category

// Created by Usmon Abdurakhmanv on 6/12/2022.

class CategorySpinnerAdapter : BaseAdapter() {

    private val categories = Category.values()

    override fun getCount(): Int = categories.size + 1

    override fun getItem(position: Int): Category? {
        if (position == 0) return null
        return categories[position - 1]
    }

    override fun getItemId(position: Int): Long {
        return View.generateViewId().toLong()
    }

    @SuppressLint("ViewHolder", "ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return SpinnerItemDropdownBinding.inflate(LayoutInflater.from(parent?.context), parent, false).apply {
            if (position != 0) category.text = Category.getCapitalizedNameByPosition(position)
        }.root
    }

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }
}

