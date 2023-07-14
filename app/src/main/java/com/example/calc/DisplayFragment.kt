package com.example.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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

    fun getInputText(): String {
        return inputTextView.text.toString()
    }

    fun setOutputText(text: String) {
        displayTextView.text = text
    }

    companion object {
        fun newInstance(): DisplayFragment {
            return DisplayFragment()
        }
    }
}