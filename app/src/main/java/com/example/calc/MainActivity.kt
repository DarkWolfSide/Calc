package com.example.calc

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.calc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FirstFragment.OnButtonClickListener {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var displayFragment: SecondFragment
    private lateinit var buttonFragment: FirstFragment
    private var equation: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape)
        } else {
            setContentView(R.layout.activity_main)
        }

        buttonFragment = FirstFragment.newInstance()
        displayFragment = SecondFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, buttonFragment)
                .replace(R.id.container2, displayFragment)
                .commit()
        }

        buttonFragment.onButtonClickListener = this

        val serviceIntent = Intent(this, MyFirstService::class.java)
        startForegroundService(serviceIntent)
        bindService(
            serviceIntent,
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    if (service != null && service is MyFirstService.MyBinder) {
                        service.getService().progress = {
                            updateDisplay("$it")
                        }
                    }
                }

                override fun onServiceDisconnected(name: ComponentName?) {

                }
            },
            BIND_AUTO_CREATE
        )

        val broadcastManager = LocalBroadcastManager.getInstance(this)
        broadcastManager.registerReceiver(
            MyFirstBroadcastReceiver(),
            IntentFilter.create("MyTestAction", "*/*"),
        )

        if (savedInstanceState != null) {
            equation = StringBuilder(savedInstanceState.getString("equation", ""))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onButtonClick(buttonText: String) {
        when (buttonText) {
            "=" -> {
                val result = calculateResult(equation.toString())
                val formattedResult = if (result % 1 == 0.0) {
                    result.toLong().toString()
                } else {
                    result.toString()
                }
                displayFragment.updateText(formattedResult)
                equation.clear()
            }
            "Backspace" -> {
                if (equation.isNotEmpty()) {
                    equation.deleteCharAt(equation.length - 1)
                    displayFragment.updateText(equation.toString())
                }
            }
            "C" -> {
                equation.clear()
                displayFragment.updateText("")
            }
            else -> {
                equation.append(buttonText)
                displayFragment.updateText(equation.toString())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("equation", equation.toString())
    }

    private fun calculateResult(equation: String): Double {
        return try {
            val expression = net.objecthunter.exp4j.ExpressionBuilder(equation).build()
            expression.evaluate()
        } catch (e: Exception) {
            Double.NaN
        }
    }

    override fun updateDisplay(text: String) {
        displayFragment.updateDisplay(text)
    }
}