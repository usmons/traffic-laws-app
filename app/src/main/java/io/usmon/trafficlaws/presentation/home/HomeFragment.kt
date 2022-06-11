package io.usmon.trafficlaws.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.usmon.trafficlaws.R
import io.usmon.trafficlaws.databinding.FragmentHomeBinding
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.base.BaseFragment
import io.usmon.trafficlaws.presentation.home.adapter.HomePagerAdapter

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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_law -> {
                //TODO: Navigate to add_edit fragment.
            }
        }
        return super.onOptionsItemSelected(item)
    }
}