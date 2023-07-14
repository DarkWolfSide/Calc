package com.example.calc

import DisplayFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ButtonFragment.OnButtonClickListener {

    private lateinit var displayFragment: DisplayFragment
    private lateinit var buttonFragment: ButtonFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFragment = ButtonFragment.newInstance()
        displayFragment = DisplayFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.display_container, displayFragment)
            .replace(R.id.button_container, buttonFragment)
            .commit()
    }

    override fun onButtonClick(buttonText: String) {
        val currentText = displayFragment.getInputText()

        if (buttonText == "Clear") {
            displayFragment.setInputText("")
            displayFragment.setOutputText("0")
        } else if (buttonText == "=") {
            try {
                val expression = currentText.replace(" ", "")
                val result = displayFragment.evaluateExpression(expression)
                displayFragment.setOutputText(result)
            } catch (e: Exception) {
                displayFragment.setOutputText("Error")
            }
        } else {
            val updatedText = currentText + buttonText + " "
            displayFragment.setInputText(updatedText)
        }
    }
}
