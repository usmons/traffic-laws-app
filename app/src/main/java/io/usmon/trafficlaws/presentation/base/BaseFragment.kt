package io.usmon.trafficlaws.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import io.usmon.trafficlaws.presentation.MainActivity

// Created by Usmon Abdurakhmanv on 6/7/2022.

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(
) {

    /**
     * This is for view model stuff.
     */
    protected abstract val viewModel: VM

    /**
     * This is for initializing view binding automatically
     */
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    protected abstract fun myCreateView(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(inflater, container, false)
        if (_binding == null)
            throw IllegalStateException("View binding couldn't be null")
        myCreateView(savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * This is for controlling visibility of bottom navigation in the MainActivity
     */

    protected lateinit var setVisibilityToBottomNavigation: ((Boolean) -> Unit)

    private fun setVisibilityToBottomNavigation(function: (Boolean) -> Unit) {
        setVisibilityToBottomNavigation = function
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        /**
         * I don't want to save context on any variable,
         * just because memory leak could be happened.
         * I created lambda function for controlling bottom navigation visibility.
         *
         * When AddEditFragment Attached it will hide and
         * when fragment destroyed it'll automatically showed again.
         *
         * I hope you got it, coz my english is not enough to convey my thoughts :(
         */

        //This is smart casting)
        if (context is MainActivity) {
            setVisibilityToBottomNavigation {
                context.setVisibilityBottomNavigation(it)
            }
        }
    }
}