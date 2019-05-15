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
    var num1: Int = 0
    var num2: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        View.OnClickListener {

            var nowText = ""
            when (it.id) {
                R.id.btn_0 -> nowText = "0"
                R.id.btn_1 -> nowText = "1"
                R.id.btn_2 -> nowText = "2"
                R.id.btn_3 -> nowText = "3"
                R.id.btn_star -> nowText = "*"
                R.id.btn_clean -> nowText = "0"

            }

            val previousText = textView?.text?.toString()
            if (nowText == "/" || nowText == "*" || nowText == "-" || nowText == "+") {

            }

            textView?.text = nowText
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
            btn = view.findViewById(R.id.btn_star)
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
