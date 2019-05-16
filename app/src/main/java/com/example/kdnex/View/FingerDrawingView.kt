package com.example.kdnex.View

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class FingerDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val path: Path
    private val pathPaint: Paint
    private val backgroundPaint: Paint


    private var backdroundCanvas: Canvas? = null
    private var backgroundBitmap: Bitmap? = null


    init {
        path = Path()
        pathPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = Color.RED
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 12.toFloat()
        }

        backgroundPaint = Paint(Paint.DITHER_FLAG)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        backgroundBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        backgroundBitmap?.let { backdroundCanvas = Canvas(it) }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundBitmap?.let {
            canvas?.drawBitmap(it, 0f, 0f, backgroundPaint)
            canvas?.drawPath(path, pathPaint)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                backdroundCanvas?.drawPath(path, pathPaint)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                path.reset()
                performClick()
                invalidate()
            }
        }

        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}