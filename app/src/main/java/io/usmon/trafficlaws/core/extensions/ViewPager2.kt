package io.usmon.trafficlaws.core.extensions

import androidx.viewpager2.widget.ViewPager2

// Created by Usmon Abdurakhmanv on 6/11/2022.

fun ViewPager2.onPageSelected(function: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            function(position)
        }
    })
}