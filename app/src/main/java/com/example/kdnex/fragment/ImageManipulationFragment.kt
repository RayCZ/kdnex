package com.example.kdnex.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment


class ImageManipulationFragment : Fragment() {
    var btn_loadimage: Button? = null
    var btn_clear: Button? = null
    var imageView: ImageView? = null
    var dx: Float = 0.toFloat()
    var dy: Float = 0.toFloat()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.kdnex.R.layout.fragment_image_manipulation, container, false)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_loadimage = view.findViewById(com.example.kdnex.R.id.btn_loadimage)
        btn_clear = view.findViewById(com.example.kdnex.R.id.btn_clear)
        imageView = view.findViewById(com.example.kdnex.R.id.imageView)

        btn_clear?.setOnClickListener {
            imageView?.setImageResource(0)
        }

        btn_loadimage?.setOnClickListener {
            val intent = Intent().apply {
                type = ("image/")
                action = Intent.ACTION_GET_CONTENT
                putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
            }
            startActivityForResult(Intent.createChooser(intent, "selection picture"), PICK_IMAGE)
        }

        imageView?.setOnTouchListener { v, event ->
            if (event.pointerCount == 1) {
                moveView(v, event)
            }

            if (event.pointerCount == 2) {
                rotateView(v, event)
            }
            v.invalidate()

            return@setOnTouchListener true
        }
    }


    fun moveView(view: View, event: MotionEvent?) {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val layoutPara: RelativeLayout.LayoutParams = view.layoutParams as RelativeLayout.LayoutParams
                dx = event.rawX - layoutPara.leftMargin
                dy = event.rawY - layoutPara.topMargin
            }

            MotionEvent.ACTION_MOVE -> {
                val layoutPara: RelativeLayout.LayoutParams = view.layoutParams as RelativeLayout.LayoutParams
                layoutPara.leftMargin = (event.rawX - dx).toInt()
                layoutPara.topMargin = (event.rawY - dy).toInt()
                layoutPara.rightMargin = 0
                layoutPara.bottomMargin = 0
                view.layoutParams = layoutPara
            }
        }
    }


    var mRotation: Float = 0.toFloat()
    fun rotateView(v: View, event: MotionEvent?) {
        when (event?.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                mRotation = rotation(event)
            }

            MotionEvent.ACTION_MOVE -> {
                val rotation = rotation(event)
                val delta = rotation - mRotation
                if (delta > 0) {
                    v.rotation += 5
                } else {
                    v.rotation -= 5
                }
            }
        }
    }

    private fun rotation(event: MotionEvent): Float {
        val delta_x = (event.getX(0) - event.getX(1)).toDouble()
        val delta_y = (event.getY(0) - event.getY(1)).toDouble()
        val radians = Math.atan2(delta_y, delta_x)
        return Math.toDegrees(radians).toFloat()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
                val uri: Uri? = data?.data
                imageView?.setImageURI(uri)
            }
        } catch (e: Exception) {
            Log.e("FileSelectorActivity", "File select error", e)
        }

    }

    companion object {
        const val PICK_IMAGE = 125
    }


}
