package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ButtonFragment.OnButtonClickListener {

    private lateinit var displayFragment: DisplayFragment
    private lateinit var buttonFragment: ButtonFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayFragment = DisplayFragment.newInstance()
        buttonFragment = ButtonFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.button_container, buttonFragment)
            .replace(R.id.display_container, displayFragment)
            .commit()
    }

    override fun onButtonClick(buttonText: String) {
        val currentText = displayFragment.getInputText()

        when (buttonText) {
            "Clear" -> {
                displayFragment.setInputText("")
                displayFragment.setOutputText("0")
            }
            "=" -> {
                try {
                    val expression = currentText.replace(" ", "")
                    val result = evaluateExpression(expression)
                    displayFragment.setOutputText(result)
                } catch (e: Exception) {
                    displayFragment.setOutputText("Error")
                }
            }
            else -> {
                val updatedText = "$currentText$buttonText "
                displayFragment.setInputText(updatedText)
            }
        }
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