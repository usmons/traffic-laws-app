package io.usmon.trafficlaws.presentation.add_edit

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.usmon.trafficlaws.core.extensions.*
import io.usmon.trafficlaws.databinding.FragmentAddEditBinding
import io.usmon.trafficlaws.domain.model.Category
import io.usmon.trafficlaws.presentation.add_edit.adapter.CategorySpinnerAdapter
import io.usmon.trafficlaws.presentation.add_edit.util.AddEditChannel
import io.usmon.trafficlaws.presentation.add_edit.util.AddEditEvent
import io.usmon.trafficlaws.presentation.base.BaseFragment

@AndroidEntryPoint
class AddEditFragment : BaseFragment<AddEditViewModel, FragmentAddEditBinding>(FragmentAddEditBinding::inflate) {


    override val viewModel: AddEditViewModel by viewModels()

    override fun myCreateView(savedInstanceState: Bundle?) {

        //TODO: we should set action bar title here

        setVisibilityToBottomNavigation(false)

        binding.category.adapter = CategorySpinnerAdapter()

        // UI state observer
        collect(viewModel.addEditState) { state ->
            binding.title.update(state.title)
            binding.description.update(state.desc)
            binding.category.updateCategory(state.category)
            binding.image.setImageBitmap(state.image ?: return@collect)
        }


        binding.title.setOnTextChangeListener {
            viewModel.onEvent(AddEditEvent.TitleChanged(it))
        }

        binding.description.setOnTextChangeListener {
            viewModel.onEvent(AddEditEvent.DescriptionChanged(it))
        }

        binding.category.onItemSelected<Category> {
            viewModel.onEvent(AddEditEvent.CategoryChanged(it))
        }

        // UI event observer
        collect(viewModel.addEditChannel) { event ->
            when (event) {
                is AddEditChannel.ShowSnackbar -> {
                    Snackbar.make(
                        binding.root,
                        event.message.asString(requireContext()),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is AddEditChannel.Success -> {
                    findNavController().popBackStack()
                }
            }
        }

    }


    override fun onDestroyView() {
        setVisibilityToBottomNavigation(true)
        super.onDestroyView()
    }
}