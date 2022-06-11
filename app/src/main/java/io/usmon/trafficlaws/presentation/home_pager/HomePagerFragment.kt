package io.usmon.trafficlaws.presentation.home_pager

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.usmon.trafficlaws.core.Constants.CATEGORY_STATE
import io.usmon.trafficlaws.databinding.FragmentHomePagerBinding
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.base.BaseFragment

@AndroidEntryPoint
class HomePagerFragment : BaseFragment<HomePagerViewModel, FragmentHomePagerBinding>(FragmentHomePagerBinding::inflate) {

    override val viewModel: HomePagerViewModel by viewModels()

    override fun myCreateView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(category: Category) = HomePagerFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CATEGORY_STATE, category)
            }
        }
    }
}
