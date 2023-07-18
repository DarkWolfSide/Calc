package com.example.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.calc.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var viewBinding: FragmentFirstBinding? = null
    var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return viewBinding?.root ?: inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.run {
            val buttons = listOf(
                back, clear, div, seven, eight, nine, mul,
                four, five, six, min, one, two, three, plus,
                zero, point, equals
            )

            val buttonClickListener = View.OnClickListener { v ->
                val buttonText = (v as Button).text.toString()
                onButtonClickListener?.onButtonClick(buttonText)
            }

            buttons.forEach { button ->
                button.setOnClickListener(buttonClickListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        fun newInstance() = FirstFragment()
    }

    interface OnButtonClickListener {
        fun onButtonClick(buttonText: String)
        fun updateDisplay(text: String)
    }
}