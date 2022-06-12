package io.usmon.trafficlaws.presentation.home_pager

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.usmon.trafficlaws.core.Constants.CATEGORY_STATE
import io.usmon.trafficlaws.core.extensions.collect
import io.usmon.trafficlaws.databinding.FragmentHomePagerBinding
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.base.BaseFragment
import io.usmon.trafficlaws.presentation.home_pager.adapter.PagerListAdapter
import io.usmon.trafficlaws.presentation.home_pager.util.HomePagerChannel
import io.usmon.trafficlaws.presentation.home_pager.util.HomePagerEvent

@AndroidEntryPoint
class HomePagerFragment : BaseFragment<HomePagerViewModel, FragmentHomePagerBinding>(FragmentHomePagerBinding::inflate) {

    override val viewModel: HomePagerViewModel by viewModels()

    override fun myCreateView(savedInstanceState: Bundle?) {

        // List View events
        val listAdapter = PagerListAdapter { event ->
            when (event) {
                is PagerListAdapter.Event.ItemClick -> {
                    viewModel.onEvent(HomePagerEvent.LawClicked(event.law))
                }
                is PagerListAdapter.Event.EditItemClick -> {
                    viewModel.onEvent(HomePagerEvent.EditLawClicked(event.law))
                }
                is PagerListAdapter.Event.DeleteItemClick -> {
                    viewModel.onEvent(HomePagerEvent.DeleteLawClicked(event.law))
                }
                is PagerListAdapter.Event.LikeItemClick -> {
                    viewModel.onEvent(HomePagerEvent.LikeLawClicked(event.law))
                }
            }
        }.also { binding.listView.adapter = it }

        // UI state
        collect(viewModel.homePagerState) { laws ->
            listAdapter.submitList(laws)
            binding.itemsNotAvailable.isVisible = laws.isEmpty()
        }

        //UI events
        collect(viewModel.homePagerChannel) { event ->
            when (event) {
                is HomePagerChannel.Details -> {
                    //TODO: Navigate to details
                }
                is HomePagerChannel.Edit -> {
                    //TODO: Navigate to edit
                }
                is HomePagerChannel.ShowSnackbar -> {
                    Snackbar
                        .make(
                            binding.root,
                            event.message.asString(requireContext()),
                            Snackbar.LENGTH_LONG
                        )
                        .setAction("Undo") {
                            viewModel.onEvent(HomePagerEvent.UndoClicked)
                        }.show()
                }
            }
        }

    }

    companion object {
        fun newInstance(category: Category) = HomePagerFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CATEGORY_STATE, category)
            }
        }
    }
}
