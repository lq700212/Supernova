package com.supernova.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan

/**
 * @author 作者：ryan.lei
 * @date 创建时间：2021/12/1 16:11
 * @Description 描述：带圆角的有背景色块的高亮span
 **/
class RoundBackgroundColorSpan(
    private val mBgColor: Int,
    private val mTextColor: Int,
    private var mTextSize: Float,
    private val mRadius: Float
) : ReplacementSpan() {
    private var originTextH: Float = 0.0f
    private var nowTextH: Float = 0.0f
    private var diffH: Float = 0.0f
    private var mSize: Float = 0.0f

    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        //关键字绘制前我们控件文本大小是设置了的。而我们想要关键字不一样大小，
        //则需要获取原始大小，然后根据关键字大小来计算边框大小，位置，字体绘制位置等
        originTextH = (paint.descent() - paint.ascent())
        // 是否需要保证关键字最多为文本大小？
        mTextSize = if (mTextSize > paint.textSize) paint.textSize else mTextSize
        // 后续计算均是以我们设置了关键字大小计算的
        paint.textSize = mTextSize
        //绘制整体往上平移，以文本顶部为准
        nowTextH = paint.descent() - paint.ascent()
        diffH = (originTextH - nowTextH) / 2; // 平移后超过多了点，少平移一些
        mSize = paint.measureText(text, start, end)
        return mSize.toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val originalColor = paint.color
        val defaultStrokeWidth = paint.strokeWidth
        //绘制圆角矩形
        paint.color = mBgColor
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2f
        paint.isAntiAlias = true
        //画圆角矩形背景
        val oval = RectF(
            x + 2.5f,
            y + paint.ascent() - 2 - diffH,
            x + mSize + mRadius * 2,
            y + paint.descent() + 2 - diffH
        )
        //设置文字背景矩形，x为span其实左上角相对整个TextView的x值，y为span左上角相对整个View的y值。paint.ascent()获得文字上边缘，paint.descent()获得文字下边缘
        canvas.drawRoundRect(oval, mRadius, mRadius, paint);//绘制圆角矩形，第二个参数是x半径，第三个参数是y半径

        //画文字
        paint.color = mTextColor
        paint.style = Paint.Style.FILL
        paint.textSize = mTextSize
        paint.strokeWidth = defaultStrokeWidth
        text?.let { canvas.drawText(it, start, end, x + mRadius, y - diffH, paint) }

        //将paint复原
        paint.color = originalColor
    }
}