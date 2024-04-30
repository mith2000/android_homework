package com.triad.mvvmlearning.view.attraction.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.FragmentAttractionDetailBinding
import com.triad.mvvmlearning.model.ATTRACTION_URL_KEY
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.repository.AttractionDetailRepository
import com.triad.mvvmlearning.view.BaseFragment
import com.triad.mvvmlearning.view.webView.WebViewActivity


class AttractionDetailFragment :
    BaseFragment<AttractionDetailViewModel, FragmentAttractionDetailBinding, AttractionDetailRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleArgument(arguments)

        viewModel.attraction.observe(viewLifecycleOwner) {
            binding.attraction = it

            if (it.images.isNotEmpty()) {
                /// I have tried MotionLayout but it didn't work
                /// https://developer.android.com/develop/ui/views/animations/motionlayout/carousel
                /// I have tried CarouselLayoutManager from Material but was limited by time with library error
                /// So I will use CarouselView
                binding.carousel.setImageListener { position, imageView ->
                    Glide.with(imageView.context)
                        .load(it.images[position].getImageLink())
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(imageView)
                }
                binding.carousel.pageCount = it.images.size
            } else {
                binding.carousel.setImageListener { _, imageView -> imageView.setImageResource(R.drawable.image_placeholder) }
                binding.carousel.pageCount = 1
            }
        }

        binding.backButton.setOnClickListener { view.findNavController().popBackStack() }

        binding.tvURL.paint.isUnderlineText = true // add underline
        binding.tvURL.setOnClickListener { onUrlTextClicked() }
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

    private fun onUrlTextClicked() {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra(ATTRACTION_URL_KEY, viewModel.attraction.value?.url)
        startActivity(intent)
    }
}