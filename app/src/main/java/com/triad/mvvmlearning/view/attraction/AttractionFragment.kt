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
            for (languageOption in AttractionViewModel.LanguageOption.values()) {
                popupMenu.menu.add(languageOption.label)
            }

            popupMenu.setOnMenuItemClickListener { menuItem ->
                val selectedLanguageLabel = menuItem.title.toString()
                val selectedLanguageOption = AttractionViewModel.LanguageOption.values()
                    .find { it.label == selectedLanguageLabel }

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


    override fun getViewModel() = AttractionViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_attraction, container, false)
    }

    override fun getFragmentRepository(): AttractionRepository {
        return AttractionRepository(remoteDataSource.buildApi(AttractionApi::class.java))
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
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
        Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("amount" to "data")
        view?.findNavController()?.navigate(R.id.action_attractionFragment_to_attractionDetailFragment, bundle)
    }

}