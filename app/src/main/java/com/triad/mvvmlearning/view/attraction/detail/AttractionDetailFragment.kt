package com.triad.mvvmlearning.view.attraction.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.FragmentAttractionDetailBinding
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.repository.AttractionDetailRepository
import com.triad.mvvmlearning.view.BaseFragment


class AttractionDetailFragment :
    BaseFragment<AttractionDetailViewModel, FragmentAttractionDetailBinding, AttractionDetailRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    override fun getViewModel() = AttractionDetailViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_attraction_detail, container, false
        )
    }

    override fun getFragmentRepository(): AttractionDetailRepository {
        return AttractionDetailRepository(remoteDataSource.buildApi(AttractionApi::class.java))
    }

}