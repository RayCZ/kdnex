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
    var sum: Double = 0.0
    var previousNumber: Double = 0.0
    var operatorSignal = ""
    var isFinishing = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    fun calculateNumber(operator: String, num1: Double, numb2: Double): Double {
        return when (operator) {
            "+" -> (num1 + numb2)
            "-" -> (num1 - numb2)
            "*" -> (num1 * numb2)
            "/" -> (num1 / numb2)
            else -> 0.0
        }
    }

    fun isSignal(str: String): Boolean {
        return str == "+" || str == "-" || str == "*" || str == "/" || str == "="
    }

    fun isNumeric(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (exception: Exception) {
            false
        }
    }

    fun isZero(str: String): Boolean {
        return try {
            str.toDouble() == 0.0
        } catch (exception: Exception) {
            false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        textView?.text = "0"


        View.OnClickListener {
            if (it.id == R.id.btn_clean) {
                previousNumber = 0.0
                operatorSignal = ""
                sum = 0.0
                isFinishing = false
                textView?.text = "0"
                return@OnClickListener
            }

            val clickedText = when (it.id) {
                R.id.btn_0 -> "0"
                R.id.btn_1 -> "1"
                R.id.btn_2 -> "2"
                R.id.btn_3 -> "3"
                R.id.btn_4 -> "4"
                R.id.btn_5 -> "5"
                R.id.btn_6 -> "6"
                R.id.btn_7 -> "7"
                R.id.btn_8 -> "8"
                R.id.btn_9 -> "9"
                R.id.btn_slash -> "/"
                R.id.btn_multiply -> "*"
                R.id.btn_minus -> "-"
                R.id.btn_plus -> "+"
                R.id.btn_equal -> "="
                else -> ""
            }

            val showText = textView?.text.toString()

            // 顯示為 0 並不點擊符號，邀免 0XXXX
            if (isZero(showText) && !isSignal(clickedText)) {
                textView?.text = clickedText
                return@OnClickListener
            }

            // 等號顯示計算結果
            if (clickedText == "=") {
                operatorSignal = ""

                if (sum == 0.0) {
                    if (isSignal(showText)) {
                        sum = previousNumber
                    } else {
                        sum = showText.toDouble()
                    }

                }

                textView?.text = sum.toString()
                isFinishing = true
                return@OnClickListener
            }

            // 點擊運算符而目前顯示不是運算符，就儲存數字、符號
            if (clickedText == "+" || clickedText == "-" || clickedText == "*" || clickedText == "/") {
                if (!isSignal(showText)) {

                    if (operatorSignal != "") {
                        previousNumber = sum
                    } else {
                        previousNumber = try {
                            showText.toDouble()
                        } catch (exception: Exception) {
                            0.0
                        }
                    }

                }

                operatorSignal = clickedText
                textView?.text = operatorSignal
                return@OnClickListener
            }

            val concatText: String
            if (isSignal(showText) || isFinishing) {
                concatText = clickedText
                isFinishing = false
            } else {
                concatText = showText + clickedText
            }
            textView?.text = concatText

            sum = calculateNumber(operatorSignal, previousNumber, concatText.toDouble())

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
