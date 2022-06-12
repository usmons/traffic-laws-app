package io.usmon.trafficlaws.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.usmon.trafficlaws.R
import io.usmon.trafficlaws.core.extensions.collect
import io.usmon.trafficlaws.core.extensions.onPageSelected
import io.usmon.trafficlaws.databinding.FragmentHomeBinding
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.base.BaseFragment
import io.usmon.trafficlaws.presentation.home.adapter.HomePagerAdapter
import io.usmon.trafficlaws.presentation.home.util.HomeChannel
import io.usmon.trafficlaws.presentation.home.util.HomeEvent

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun myCreateView(savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        actionBar { title = getString(R.string.app_name) }

        binding.pager.adapter = HomePagerAdapter(this)

        TabLayoutMediator(
            binding.tabLayout,
            binding.pager
        ) { tab, position ->
            tab.text = Category.getCapitalizedNameByPosition(position)
        }.attach()

        //UI state
        collect(viewModel.homeState) { state ->
            binding.pager.currentItem = state.pagerPosition
        }

        binding.pager.onPageSelected { position ->
            viewModel.onEvent(HomeEvent.PagerChanged(position))
        }

        //Events channel
        collect(viewModel.homeChannel) { event ->
            when (event) {
                HomeChannel.AddLaw -> {
                    //TODO: Navigate to add_edit fragment
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_law -> {
                viewModel.onEvent(HomeEvent.AddLaw)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}