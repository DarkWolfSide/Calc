import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.calc.R
class MainActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView
    private lateinit var inputEditText: EditText
    private lateinit var calculateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)
        inputEditText = findViewById(R.id.inputEditText)
        calculateButton = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val inputExpression = inputEditText.text.toString().trim()
            val result = evaluateExpression(inputExpression)
            outputTextView.text = result
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