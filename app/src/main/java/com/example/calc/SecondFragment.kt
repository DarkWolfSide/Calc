package com.example.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(), OnButtonClickListener {
    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        inputTextView = view.findViewById(R.id.input)
        outputTextView = view.findViewById(R.id.output)
        savedInstanceState?.getString(TEXT)?.let {
            println("RESTORE STATE $it")
            outputTextView.text = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val outputTextValue = outputTextView.text?.toString()
        outState.putString(TEXT, outputTextValue)
        println("SAVE STATE $outputTextValue")
    }

    override fun onButtonClick(value: String) {
        updateText(value)
    }

    fun updateText(value: String) {
        outputTextView.text = value
    }

    fun updateDisplay(text: String) {
        outputTextView?.text = text
    }

    companion object {
        private const val TEXT = "TEXT"

        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }
}