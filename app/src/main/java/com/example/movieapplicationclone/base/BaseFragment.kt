package com.example.movieapplicationclone.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment(),
    View.OnClickListener {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw IllegalArgumentException("Binding can not be null!")
        onBeforeOnCreateView(savedInstanceState)
        return _binding!!.root
    }

    abstract fun setupUI(savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setupUI(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    protected open fun observe() {
    }

    open fun onBeforeOnCreateView(savedInstanceState: Bundle?) {
    }

    open fun hideProgressBar() {}

    open fun showProgressBar() {}

    open fun setupRecyclerView() {}

    open fun observeData() {}
}