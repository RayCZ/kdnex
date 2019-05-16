package com.example.kdnex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kdnex.R

class CalculatorFragment : Fragment() {
    var textView: TextView? = null
    var sum: Int = 0

    var isAfterSignal = false
    var signalText = ""

    var previousNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        textView?.text = "0"
        View.OnClickListener {
            var nowClickedText = ""
            when (it.id) {
                R.id.btn_0 -> nowClickedText = "0"
                R.id.btn_1 -> nowClickedText = "1"
                R.id.btn_2 -> nowClickedText = "2"
                R.id.btn_3 -> nowClickedText = "3"
                R.id.btn_4 -> nowClickedText = "4"
                R.id.btn_5 -> nowClickedText = "5"
                R.id.btn_6 -> nowClickedText = "6"
                R.id.btn_7 -> nowClickedText = "7"
                R.id.btn_8 -> nowClickedText = "8"
                R.id.btn_9 -> nowClickedText = "9"
                R.id.btn_slash -> nowClickedText = "/"
                R.id.btn_multiply -> nowClickedText = "*"
                R.id.btn_minus -> nowClickedText = "-"
                R.id.btn_plus -> nowClickedText = "+"
                R.id.btn_clean -> {
                    sum = 0
                    textView?.text = "0"
                    return@OnClickListener
                }
            }

            val nowText = textView?.text.toString()

            // concat numeric
            if (!(nowClickedText == "+" || nowClickedText == "-" || nowClickedText == "*" || nowClickedText == "/" || nowClickedText == "=")) {
                if (nowText == "0") {
                    textView?.text = nowClickedText
                } else {
                    val concatText = nowText + nowClickedText
                    textView?.text = concatText
                }
                return@OnClickListener
            }

            if (nowClickedText == "+" || nowClickedText == "-" || nowClickedText == "*" || nowClickedText == "/") {
                previousNumber = try {
                    nowText.toInt()
                } catch (exception: Exception) {
                    0
                }
            }

//            if (nowClickedText == "+" || nowClickedText == "-" || nowClickedText == "*" || nowClickedText == "/") {
//                signalText = nowClickedText
//
//
//                textView?.text = nowClickedText
//                isAfterSignal = true
//            } else if (nowClickedText == "=") {
//                textView?.text = sum.toString()
//                sum = 0
//                isAfterSignal = false
//            } else { // is clicked numeric
//
//            }


        }.let {
            var btn: Button? = view.findViewById(R.id.btn_0)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_1)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_2)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_3)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_4)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_5)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_6)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_7)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_8)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_9)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_slash)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_multiply)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_minus)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_plus)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_equal)
            btn?.setOnClickListener(it)
            btn = view.findViewById(R.id.btn_clean)
            btn?.setOnClickListener(it)
        }

    }
}
