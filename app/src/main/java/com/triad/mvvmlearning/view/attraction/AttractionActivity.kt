package com.triad.mvvmlearning.view.attraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.ActivityAttractionBinding

class AttractionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAttractionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_attraction)
    }

}