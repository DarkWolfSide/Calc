package com.example.calc
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
class ButtonsActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
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
    private lateinit var buttonBack: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonPoint: Button
    private lateinit var buttonClear: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
        inputTextView = findViewById(R.id.input)
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
        buttonBack = findViewById(R.id.back)
        buttonPlus = findViewById(R.id.plus)
        buttonMinus = findViewById(R.id.min)
        buttonMul = findViewById(R.id.mul)
        buttonDiv = findViewById(R.id.div)
        buttonPoint = findViewById(R.id.point)
        buttonClear = findViewById(R.id.clear)

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

        buttonEquals = findViewById(R.id.equals)
        buttonEquals.setOnClickListener {
            try {
                val expression = getInputExpression()
                val result = evaluateExpression(expression)
                outputTextView.text = result
            } catch (e: Exception) {
                outputTextView.text = "Error"
            }
        }
    }
}