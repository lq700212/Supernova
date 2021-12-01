package com.supernova.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.widget.TextView
import java.util.regex.Pattern

/**
 * @author 作者：ryan.lei
 * @date 创建时间：2021/12/1 16:57
 * @Description 描述：字符串处理工具类
 **/
class StringUtil {
    companion object {
        /**
         * 高亮textview中的关键字并添加色块阴影
         *
         * @param tv 被处理的textview
         * @param backgroundColor 高亮背景色块
         * @param textColor 高亮文字颜色
         * @param keyword 关键字
         */
        fun highlightKeyWordWithBackground(
            tv: TextView,
            textColor: String,
            backgroundColor: String,
            keyword: String
        ) {
            if (keyword.length > tv.text.toString().length) {
                return
            }
            val span = SpannableString(tv.text.toString());
            val pattern = Pattern.compile(keyword, Pattern.LITERAL or Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(tv.text.toString())
            while (matcher.find()) {
                span.setSpan(
                    RoundBackgroundColorSpan(
                        Color.parseColor(backgroundColor),
                        Color.parseColor(textColor),
                        tv.textSize,
                        0f
                    ),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                tv.text = span
            }
        }
    }
}