package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView
    private lateinit var buttonZero: Button
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button

    private lateinit var buttonEquals: Button
    private lateinit var buttonPlusMinus: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonPoint: Button
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)

        buttonZero = findViewById(R.id.zero)
        buttonOne = findViewById(R.id.one)
        buttonTwo = findViewById(R.id.two)
        buttonThree = findViewById(R.id.three)
        buttonFour = findViewById(R.id.four)
        buttonFive = findViewById(R.id.five)
        buttonSix = findViewById(R.id.six)
        buttonSeven = findViewById(R.id.seven)
        buttonEight = findViewById(R.id.eight)
        buttonNine = findViewById(R.id.nine)

        buttonEquals = findViewById(R.id.equals)
        buttonPlusMinus = findViewById(R.id.plusminus)
        buttonPlus = findViewById(R.id.plus)
        buttonMinus = findViewById(R.id.min)
        buttonMul = findViewById(R.id.mul)
        buttonDiv = findViewById(R.id.div)
        buttonPoint = findViewById(R.id.point)
        buttonClear = findViewById(R.id.clear)

        var input: String = ""
        var result: Double = 0.0

        buttonZero.setOnClickListener {
            input += "0"
            inputTextView.text = input
        }
        buttonOne.setOnClickListener {
            input += "1"
            inputTextView.text = input
        }
        buttonTwo.setOnClickListener {
            input += "2"
            inputTextView.text = input
        }
        buttonThree.setOnClickListener {
            input += "3"
            inputTextView.text = input
        }
        buttonFour.setOnClickListener {
            input += "4"
            inputTextView.text = input
        }
        buttonFive.setOnClickListener {
            input += "5"
            inputTextView.text = input
        }
        buttonSix.setOnClickListener {
            input += "6"
            inputTextView.text = input
        }
        buttonSeven.setOnClickListener {
            input += "7"
            inputTextView.text = input
        }
        buttonEight.setOnClickListener {
            input += "8"
            inputTextView.text = input
        }
        buttonNine.setOnClickListener {
            input += "9"
            inputTextView.text = input
        }

        buttonPlus.setOnClickListener {
            input += "+"
            inputTextView.text = input
        }
        buttonMinus.setOnClickListener {
            input += "-"
            inputTextView.text = input
        }
        buttonMul.setOnClickListener {
            input += "*"
            inputTextView.text = input
        }
        buttonDiv.setOnClickListener {
            input += "/"
            inputTextView.text = input
        }
//        buttonPlusMinus.setOnClickListener {
//            input += "-"
//            inputTextView.text = input
//        }
        buttonPoint.setOnClickListener {
            input += "."
            inputTextView.text = input
        }

        buttonClear.setOnClickListener {
            input = ""
            inputTextView.text = ""
            outputTextView.text = "0"
        }

        buttonEquals.setOnClickListener {
            try {
                result = evaluateExpression(input)
                // Display the result
                outputTextView.text = result.toString()
            } catch (e: Exception) {
                outputTextView.text = "Error"
            }
        }

    }
    private fun getInputExpression(): String {
        return "${inputTextView.text}"
    }

    private fun evaluateExpression(expression: String): Double {
        val terms = expression.split(" ")
        var result = 0.0
        var operator = "+"
        for (term in terms) {
            when (term) {
                "+" -> operator = "+"
                "-" -> operator = "-"
                else -> {
                    val value = term.toDouble()
                    if (operator == "+") {
                        result += value
                    } else if (operator == "-") {
                        result -= value
                    }
                }
            }
        }
        return result
    }

}