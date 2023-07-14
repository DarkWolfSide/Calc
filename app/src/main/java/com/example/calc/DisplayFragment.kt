import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calc.R

class DisplayFragment : Fragment() {
    private lateinit var displayTextView: TextView
    private lateinit var inputTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        displayTextView = view.findViewById(R.id.output)
        inputTextView = view.findViewById(R.id.input)
        return view
    }
    fun setInputText(text: String) {
        inputTextView.text = text
    }
    fun setOutputText(text: String) {
        displayTextView.text = text
    }
    fun getInputText(): String {
        return inputTextView.text.toString()
    }
    fun evaluateExpression(expression: String): String {
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
    companion object {
        fun newInstance(): DisplayFragment {
            return DisplayFragment()
        }
    }
}