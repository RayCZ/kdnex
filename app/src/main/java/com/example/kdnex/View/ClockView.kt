package com.example.kdnex.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var min: Int = 0
    private var padding: Int = 0
    private var radius: Int = 0
    private val paint: Paint
    private val centerPointPaint: Paint
    private val circlePaint: Paint
    private val numericPaint: Paint
    private val numericArray: Array<Int>

    private var isInit: Boolean = false

    init {


        paint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 8.toFloat()
        }

        centerPointPaint = Paint().apply {
            style = Paint.Style.FILL
        }

        circlePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 8.toFloat()
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        numericPaint = Paint().apply {
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13.toFloat(), resources.displayMetrics)
        }

        numericArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    }

    fun initSize() {
        min = Math.min(height, width)
        padding = 50
        radius = min / 2 - padding
        isInit = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!isInit) {
            initSize()
        }

        canvas?.let {
            drawCenterPoint(it)
            drawCircle(it)
            drawNumeric(it)
        }


    }

    fun drawNumeric(canvas: Canvas?) {
        val rect = Rect()
        numericArray.forEach {
            val text = it.toString()
            numericPaint.getTextBounds(text, 0, text.length, rect)
            val angle = Math.PI / 6 * (it - 3)
            val x = (width / 2 + Math.cos(angle) * radius - rect.width() / 2).toFloat()
            val y = (height / 2 + Math.sin(angle) * radius + rect.height() / 2).toFloat()
            canvas?.drawText(text, x, y, numericPaint)
        }


    }

    fun drawCenterPoint(canvas: Canvas?) {
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            12.toFloat(),
            centerPointPaint
        )
    }

    fun drawCircle(canvas: Canvas?) {
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (radius + padding - 10).toFloat(),
            circlePaint
        )
    }
}