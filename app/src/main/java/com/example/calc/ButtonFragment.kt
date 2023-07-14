package com.example.calc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ButtonFragment : Fragment() {
    private lateinit var buttonClickListener: OnButtonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnButtonClickListener) {
            buttonClickListener = context
        } else {
            throw IllegalStateException("Activity must implement OnButtonClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button, container, false)

        val buttonZero: Button = view.findViewById(R.id.zero)
        val buttonOne: Button = view.findViewById(R.id.one)
        val buttonTwo: Button = view.findViewById(R.id.two)
        val buttonThree: Button = view.findViewById(R.id.three)
        val buttonFour: Button = view.findViewById(R.id.four)
        val buttonFive: Button = view.findViewById(R.id.five)
        val buttonSix: Button = view.findViewById(R.id.six)
        val buttonSeven: Button = view.findViewById(R.id.seven)
        val buttonEight: Button = view.findViewById(R.id.eight)
        val buttonNine: Button = view.findViewById(R.id.nine)

        val buttonPlus: Button = view.findViewById(R.id.plus)
        val buttonMinus: Button = view.findViewById(R.id.min)
        val buttonMul: Button = view.findViewById(R.id.mul)
        val buttonDiv: Button = view.findViewById(R.id.div)

        val buttonClear: Button = view.findViewById(R.id.clear)
        val buttonBack: Button = view.findViewById(R.id.back)

        val buttonPoint: Button = view.findViewById(R.id.point)

        val buttonEquals: Button = view.findViewById(R.id.equals)

        val displayFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.display_container) as? DisplayFragment
        val inputTextView: TextView? = displayFragment?.view?.findViewById(R.id.input)

        val outputTextView: TextView = requireActivity().findViewById(R.id.output)

        var input = ""

        buttonZero.setOnClickListener {
            if (inputTextView?.text?.isNotEmpty() == true && inputTextView.text != "0 ") {
                input += "0 "
            } else {
                input = "0 "
            }
            inputTextView?.text = input
        }

        buttonOne.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "1 "
            } else {
                input += "1 "
            }
            inputTextView?.text = input
        }
        buttonTwo.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "2 "
            } else {
                input += "2 "
            }
            inputTextView?.text = input
        }
        buttonThree.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "3 "
            } else {
                input += "3 "
            }
            inputTextView?.text = input
        }
        buttonFour.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "4 "
            } else {
                input += "4 "
            }
            inputTextView?.text = input
        }
        buttonFive.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "5 "
            } else {
                input += "5 "
            }
            inputTextView?.text = input
        }
        buttonSix.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "6 "
            } else {
                input += "6 "
            }
            inputTextView?.text = input
        }
        buttonSeven.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "7 "
            } else {
                input += "7 "
            }
            inputTextView?.text = input
        }
        buttonEight.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "8 "
            } else {
                input += "8 "
            }
            inputTextView?.text = input
        }
        buttonNine.setOnClickListener {
            if (inputTextView?.text == "0 ") {
                input = "9 "
            } else {
                input += "9 "
            }
            inputTextView?.text = input
        }

        buttonBack.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(2)
                inputTextView?.text = input
                if (input.isNotEmpty()) {
                    outputTextView.text = "0"
                }
            }
        }
        buttonPlus.setOnClickListener {
            input += "+ "
            inputTextView?.text = input
        }
        buttonMinus.setOnClickListener {
            input += "- "
            inputTextView?.text = input
        }
        buttonMul.setOnClickListener {
            input += "* "
            inputTextView?.text = input
        }
        buttonDiv.setOnClickListener {
            input += "/ "
            inputTextView?.text = input
        }
        buttonPoint.setOnClickListener {
            input += "."
            inputTextView?.text = input
        }

        buttonClear.setOnClickListener {
            input = ""
            inputTextView?.text = ""
            outputTextView.text = "0"
        }

        buttonEquals.setOnClickListener {
            buttonClickListener.onButtonClick("=")
        }

        return view
    }

    interface OnButtonClickListener {
        fun onButtonClick(buttonText: String)
    }

    companion object {
        fun newInstance(): ButtonFragment {
            return ButtonFragment()
        }
    }
}