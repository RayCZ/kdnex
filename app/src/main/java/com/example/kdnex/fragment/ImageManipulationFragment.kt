package com.example.kdnex.fragment

import android.annotation.TargetApi
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment


class ImageManipulationFragment : Fragment() {
    var btn_loadimage: Button? = null
    var btn_clear: Button? = null
    var image: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.kdnex.R.layout.fragment_image_manipulation, container, false)
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_loadimage = view.findViewById(com.example.kdnex.R.id.btn_loadimage)
        btn_clear = view.findViewById(com.example.kdnex.R.id.btn_clear)
        image = view.findViewById(com.example.kdnex.R.id.image)

        btn_clear?.setOnClickListener {
            image?.setImageResource(0)
        }

        btn_loadimage?.setOnClickListener {
            val intent = Intent().apply {
                type = ("image/")
                action = Intent.ACTION_GET_CONTENT
                putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
            }
            startActivityForResult(Intent.createChooser(intent, "selection picture"), PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
                val uri: Uri? = data?.data
                image?.setImageURI(uri)
            }
        } catch (e: Exception) {
            Log.e("FileSelectorActivity", "File select error", e)
        }

    }

    companion object {
        const val PICK_IMAGE = 125
    }
}
