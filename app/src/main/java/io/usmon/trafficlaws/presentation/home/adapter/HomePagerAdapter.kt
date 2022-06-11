package io.usmon.trafficlaws.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.home_pager.HomePagerFragment

// Created by Usmon Abdurakhmanv on 6/11/2022.

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val category = when (position) {
            0 -> Category.Warning
            1 -> Category.Privileged
            2 -> Category.Prohibition
            else -> Category.Commander
        }
        return HomePagerFragment.newInstance(category)
    }
}