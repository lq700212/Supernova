package com.supernova

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.supernova.databinding.ActivityMainBinding
import com.supernova.utils.StringUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    private fun initView(binding: ActivityMainBinding) {
        binding.tvRoundBackgroundColorSpan.text = "发大水东方大厦发多发大水发大水的发生发大水sdfasfas少东方大厦范德萨发大水高亮"
        binding.etRoundBackgroundColorSpan.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                StringUtil.highlightKeyWordWithBackground(
                    binding.tvRoundBackgroundColorSpan,
                    "#EBF9F6",
                    "#00B38A",
                    binding.etRoundBackgroundColorSpan.text.toString()
                )
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}