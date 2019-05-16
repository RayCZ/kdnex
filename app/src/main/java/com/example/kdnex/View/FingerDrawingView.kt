package com.example.kdnex.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class FingerDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val path: Path
    private val backgroundPaint: Paint


    private var canvas: Canvas? = null
    private var backgroundBitmap: Bitmap? = null


    private var xPosition: Float = 0f
    private var yPosition: Float = 0f

    init {
        path = Path()
        backgroundPaint = Paint(Paint.DITHER_FLAG)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        backgroundBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        backgroundBitmap?.let { canvas = Canvas(it) }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundBitmap?.let { canvas?.drawBitmap(it, 0f, 0f, backgroundPaint) }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                path.moveTo(event.x, event.y)
                xPosition = event.x
                yPosition = event.y
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
            }
        }

        return super.onTouchEvent(event)
    }
}