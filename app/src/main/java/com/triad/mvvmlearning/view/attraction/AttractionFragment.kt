package com.triad.mvvmlearning.view.attraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.FragmentAttractionBinding
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.network.Resource
import com.triad.mvvmlearning.repository.AttractionRepository
import com.triad.mvvmlearning.view.BaseFragment


class AttractionFragment :
    BaseFragment<AttractionViewModel, FragmentAttractionBinding, AttractionRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)


        viewModel.getAllAttractions("en")

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {

                    val attractionList = it.value

                    Toast.makeText(requireContext(), "Logged In", Toast.LENGTH_SHORT).show()

                }

                is Resource.Failure -> {

                    Toast.makeText(requireContext(), "Attraction Failed", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    override fun getViewModel() = AttractionViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_attraction, container, false)
    }

    override fun getFragmentRepository(): AttractionRepository {
        return AttractionRepository(remoteDataSource.buildApi(AttractionApi::class.java))
    }

}