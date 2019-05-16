package com.example.kdnex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        textView?.text = "0"


        View.OnClickListener {
            if (it.id == R.id.btn_clean) {
                init()
                return@OnClickListener
            }

            val clickedText = getClickedText(it.id)
            val showText = textView?.text.toString()

            // 顯示為 0 並不點擊符號，邀免 0XXXX
            if (isZero(showText) && !clickedText.isSignal()) {
                textView?.text = clickedText
                return@OnClickListener
            }

            // 等號顯示計算結果
            if (clickedText == "=") {
                if (showText.isSignal()) {
                    sum = previousNumber
                } else if (operatorSignal == "" && previousNumber == 0.0) {
                    sum = showText.toDouble()
                }
                operatorSignal = ""
                textView?.text = sum.toString()
                isFinishing = true
                return@OnClickListener
            }

            // 點擊運算符而目前顯示不是運算符，就儲存數字、符號
            if (clickedText == "+" || clickedText == "-" || clickedText == "*" || clickedText == "/") {
                if (!showText.isSignal()) {

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
            if (showText.isSignal() || isFinishing) {
                concatText = clickedText
                isFinishing = false
            } else {
                concatText = showText + clickedText
            }
            textView?.text = concatText

            sum = calculateNumber(operatorSignal, previousNumber, concatText.toDouble())

        }.let {
            injectListener(view, R.id.btn_0, it)
            injectListener(view, R.id.btn_1, it)
            injectListener(view, R.id.btn_2, it)
            injectListener(view, R.id.btn_3, it)
            injectListener(view, R.id.btn_4, it)
            injectListener(view, R.id.btn_5, it)
            injectListener(view, R.id.btn_6, it)
            injectListener(view, R.id.btn_7, it)
            injectListener(view, R.id.btn_8, it)
            injectListener(view, R.id.btn_9, it)
            injectListener(view, R.id.btn_slash, it)
            injectListener(view, R.id.btn_multiply, it)
            injectListener(view, R.id.btn_minus, it)
            injectListener(view, R.id.btn_plus, it)
            injectListener(view, R.id.btn_equal, it)
            injectListener(view, R.id.btn_clean, it)
        }

    }

    fun getClickedText(@IdRes id: Int): String {
        return when (id) {
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
    }

    fun injectListener(view: View, @IdRes id: Int, listener: View.OnClickListener) {
        val btn: Button? = view.findViewById(id)
        btn?.setOnClickListener(listener)
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


    fun String.isSignal(): Boolean {
        return this == "+" || this == "-" || this == "*" || this == "/" || this == "="
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

    fun init() {
        previousNumber = 0.0
        operatorSignal = ""
        sum = 0.0
        isFinishing = false
        textView?.text = "0"
    }
}
