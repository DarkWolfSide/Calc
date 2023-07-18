package com.example.calc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyFirstBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        if (intent?.action == "Calculator") {
            println("Receiver received")
        } else {
            println("Receiver not received")
        }
    }
}