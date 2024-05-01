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

    /**
     * This function handles the setup and configuration of a WebView.
     *
     * It sets the WebViewClient for the WebView, enabling the WebView to handle its own URL loading.
     * It also enables JavaScript for the WebView, as some webpages may require it to function properly.
     *
     * The function retrieves the URL to be loaded from the intent extras using the key ATTRACTION_URL_KEY.
     * It then loads the URL into the WebView.
     *
     * @param binding The ActivityWebviewBinding instance that contains the WebView to be configured.
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun handleWebView(binding: ActivityWebviewBinding) {
        binding.webview.webViewClient = WebViewClient()

        // Enable JavaScript if the webpage requires it
        binding.webview.settings.javaScriptEnabled = true

        // retrieve the URL
        val url = intent.getStringExtra(ATTRACTION_URL_KEY)
        binding.webview.loadUrl(url)
    }

}