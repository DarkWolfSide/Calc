package com.example.calc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        buttonZero.setOnClickListener {
            buttonClickListener.onButtonClick("0")
        }
        buttonOne.setOnClickListener {
            buttonClickListener.onButtonClick("1")
        }
        buttonTwo.setOnClickListener {
            buttonClickListener.onButtonClick("2")
        }
        buttonThree.setOnClickListener {
            buttonClickListener.onButtonClick("3")
        }
        buttonFour.setOnClickListener {
            buttonClickListener.onButtonClick("4")
        }
        buttonFive.setOnClickListener {
            buttonClickListener.onButtonClick("5")
        }
        buttonSix.setOnClickListener {
            buttonClickListener.onButtonClick("6")
        }
        buttonSeven.setOnClickListener {
            buttonClickListener.onButtonClick("7")
        }
        buttonEight.setOnClickListener {
            buttonClickListener.onButtonClick("8")
        }
        buttonNine.setOnClickListener {
            buttonClickListener.onButtonClick("9")
        }

        buttonBack.setOnClickListener {
            buttonClickListener.onButtonClick("Back")
        }
        buttonPlus.setOnClickListener {
            buttonClickListener.onButtonClick("+")
        }
        buttonMinus.setOnClickListener {
            buttonClickListener.onButtonClick("-")
        }
        buttonMul.setOnClickListener {
            buttonClickListener.onButtonClick("*")
        }
        buttonDiv.setOnClickListener {
            buttonClickListener.onButtonClick("/")
        }
        buttonPoint.setOnClickListener {
            buttonClickListener.onButtonClick(".")
        }

        buttonClear.setOnClickListener {
            buttonClickListener.onButtonClick("Clear")
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
