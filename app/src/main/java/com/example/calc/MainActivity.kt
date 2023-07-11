package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.calc.R.*

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
    private lateinit var buttonBack: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonPoint: Button
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_calc)

        inputTextView = findViewById(id.input)
        outputTextView = findViewById(id.output)

        buttonZero = findViewById(id.zero)
        buttonOne = findViewById(id.one)
        buttonTwo = findViewById(id.two)
        buttonThree = findViewById(id.three)
        buttonFour = findViewById(id.four)
        buttonFive = findViewById(id.five)
        buttonSix = findViewById(id.six)
        buttonSeven = findViewById(id.seven)
        buttonEight = findViewById(id.eight)
        buttonNine = findViewById(id.nine)

        buttonEquals = findViewById(id.equals)
        buttonBack = findViewById(id.back)
        buttonPlus = findViewById(id.plus)
        buttonMinus = findViewById(id.min)
        buttonMul = findViewById(id.mul)
        buttonDiv = findViewById(id.div)
        buttonPoint = findViewById(id.point)
        buttonClear = findViewById(id.clear)

        var input: String = ""
        var result: Double = 0.0

        buttonZero.setOnClickListener {
            if (inputTextView.text.isNotEmpty() && inputTextView.text != "0 ") {
                input += "0 "
            } else {
                input = "0 "
            }
            inputTextView.text = input
        }
        buttonOne.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "1 "
            } else {
                input += "1 "
            }
            inputTextView.text = input
        }
        buttonTwo.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "2 "
            } else {
                input += "2 "
            }
            inputTextView.text = input
        }
        buttonThree.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "3 "
            } else {
                input += "3 "
            }
            inputTextView.text = input
        }
        buttonFour.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "4 "
            } else {
                input += "4 "
            }
            inputTextView.text = input
        }
        buttonFive.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "5 "
            } else {
                input += "5 "
            }
            inputTextView.text = input
        }
        buttonSix.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "6 "
            } else {
                input += "6 "
            }
            inputTextView.text = input
        }
        buttonSeven.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "7 "
            } else {
                input += "7 "
            }
            inputTextView.text = input
        }
        buttonEight.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "8 "
            } else {
                input += "8 "
            }
            inputTextView.text = input
        }
        buttonNine.setOnClickListener {
            if (inputTextView.text == "0 ") {
                input = "9 "
            } else {
                input += "9 "
            }
            inputTextView.text = input
        }

        buttonBack.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(2)
                inputTextView.text = input
                if (input.isNotEmpty()) {
                    outputTextView.text = "0"
                }
            }
        }
        buttonPlus.setOnClickListener {
            input += "+ "
            inputTextView.text = input
        }
        buttonMinus.setOnClickListener {
            input += "- "
            inputTextView.text = input
        }
        buttonMul.setOnClickListener {
            input += "* "
            inputTextView.text = input
        }
        buttonDiv.setOnClickListener {
            input += "/ "
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
                val expression = getInputExpression()
                val result = evaluateExpression(expression)
                outputTextView.text = result.toString()
            } catch (e: Exception) {
                outputTextView.text = "Error"
            }
        }

    }
    private fun getInputExpression(): String {
        return inputTextView.text.toString().replace(" ", "")
    }
    private fun evaluateExpression(expression: String): String {
        val operators = arrayOf("+", "-", "*", "/")
        val expressionParts = mutableListOf<String>()
        var currentNumber = ""
        for (char in expression) {
            if (char.isDigit() || char == '.') {
                currentNumber += char
            } else if (char.toString() in operators) {
                if (currentNumber.isNotEmpty()) {
                    expressionParts.add(currentNumber)
                    currentNumber = ""
                }
                expressionParts.add(char.toString())
            }
        }
        if (currentNumber.isNotEmpty()) {
            expressionParts.add(currentNumber)
        }
        val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2)
        val operandStack = mutableListOf<Double>()
        val operatorStack = mutableListOf<String>()
        for (term in expressionParts) {
            if (term.isOperator()) {
                while (operatorStack.isNotEmpty() && precedence[operatorStack.last()]!! >= precedence[term]!!) {
                    val value2 = operandStack.removeLast()
                    val value1 = operandStack.removeLast()
                    val operator = operatorStack.removeLast()
                    val result = performOperation(value1, value2, operator)
                    operandStack.add(result)
                }
                operatorStack.add(term)
            } else {
                operandStack.add(term.toDouble())
            }
        }
        while (operatorStack.isNotEmpty()) {
            val value2 = operandStack.removeLast()
            val value1 = operandStack.removeLast()
            val operator = operatorStack.removeLast()
            val result = performOperation(value1, value2, operator)
            operandStack.add(result)
        }
        val result = operandStack.first()
        return if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    }
    private fun performOperation(value1: Double, value2: Double, operator: String): Double {
        return when (operator) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "*" -> value1 * value2
            "/" -> value1 / value2
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }
    }
    private fun String.isOperator(): Boolean {
        return this in arrayOf("+", "-", "*", "/")
    }
}