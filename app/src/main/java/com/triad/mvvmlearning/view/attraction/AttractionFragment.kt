package com.triad.mvvmlearning.view.attraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.FragmentAttractionBinding
import com.triad.mvvmlearning.model.ATTRACTION_MODEL_VIEW_KEY
import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.network.Resource
import com.triad.mvvmlearning.repository.AttractionRepository
import com.triad.mvvmlearning.utility.visible
import com.triad.mvvmlearning.view.BaseFragment
import com.triad.mvvmlearning.view.attraction.adapter.AttractionAdapter


class AttractionFragment :
    BaseFragment<AttractionViewModel, FragmentAttractionBinding, AttractionRepository>() {

    private lateinit var adapter: AttractionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AttractionAdapter(arrayListOf(), listener = this::attractionAdapterListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner, binding.vLoading.root::visible)
        viewModel.attractions.observe(viewLifecycleOwner, attractionsObserver())

        initRecyclerView()
        setupLanguageButton()
    }


    override fun getViewModel() = AttractionViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_attraction, container, false)
    }

    override fun getFragmentRepository(): AttractionRepository {
        return AttractionRepository(remoteDataSource.buildApi(AttractionApi::class.java))
    }

    private fun attractionsObserver(): Observer<Resource<ArrayList<AttractionModelV>>> = Observer {
        when (it) {
            is Resource.Success -> {
                adapter.addAllItems(it.value)
            }

            is Resource.Failure -> {
                Toast.makeText(requireContext(), R.string.get_data_failed, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupLanguageButton() {
        binding.languageButton.setOnClickListener { button ->
            val popupMenu = PopupMenu(context, button)
            // When the button is clicked, a PopupMenu is created with the language options.
            for (languageOption in AttractionViewModel.LanguageOption.values()) {
                popupMenu.menu.add(languageOption.label)
            }

            // When a menu item is clicked, it finds the corresponding LanguageOption.
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val selectedLanguageLabel = menuItem.title.toString()
                val selectedLanguageOption = AttractionViewModel.LanguageOption.values()
                    .find { it.label == selectedLanguageLabel }

                // If the selected LanguageOption is found, it calls the viewModel's changeLanguageOption function with the selected LanguageOption.
                if (selectedLanguageOption != null) {
                    viewModel.changeLanguageOption(selectedLanguageOption)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_found, selectedLanguageLabel),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
                true
            }

            popupMenu.show()
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        // When items are inserted into the adapter, it checks if the inserted items are at the start of the list (positionStart == 0).
        // If they are, it scrolls the RecyclerView to the start.
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                if (positionStart == 0) {
                    // When pull to refresh
                    binding.recyclerView.scrollToPosition(positionStart)
                }
            }
        })
    }

    private fun attractionAdapterListener(data: AttractionModelV) {
        val bundle = bundleOf(ATTRACTION_MODEL_VIEW_KEY to data)
        view?.findNavController()
            ?.navigate(R.id.action_attractionFragment_to_attractionDetailFragment, bundle)
    }

}