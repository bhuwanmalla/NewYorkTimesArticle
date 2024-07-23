package bhuwan.example.newyorktimes

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bhuwan.example.newyorktimes.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        val webView = binding.webView

        val url = intent.getStringExtra("web_url")
        if (!url.isNullOrEmpty()){
            webView.loadUrl(url)
        }


    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
}