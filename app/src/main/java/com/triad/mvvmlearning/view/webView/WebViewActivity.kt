package com.triad.mvvmlearning.view.webView

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.triad.mvvmlearning.R
import com.triad.mvvmlearning.databinding.ActivityWebviewBinding
import com.triad.mvvmlearning.model.ATTRACTION_URL_KEY

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWebviewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_webview)

        handleWebView(binding)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun handleWebView(binding: ActivityWebviewBinding) {
        binding.webview.webViewClient = WebViewClient()

        // Enable JavaScript if the webpage requires it
        binding.webview.settings.javaScriptEnabled = true

        val url = intent.getStringExtra(ATTRACTION_URL_KEY) // retrieve the URL
        binding.webview.loadUrl(url)
    }

}