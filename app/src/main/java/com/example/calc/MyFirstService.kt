package com.example.calc

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyFirstService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val myBinder = MyBinder()
    var progress: (Int) -> Unit = {}

    override fun onCreate() {
        super.onCreate()

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java)
                .let { notificationIntent ->
                    PendingIntent.getActivity(
                        this, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }

        val channel = "TestChannel"

        val notification: Notification = Notification
            .Builder(this, "CHANNEL_DEFAULT_IMPORTANCE")
            .setContentTitle("Foreground service")
            .setContentText("My service is working")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setChannelId(channel)
            .build()

        val notificationChannel = NotificationChannel(
            channel, channel, NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager: NotificationManager? = getSystemService()
        notificationManager?.notify(1, notification)
        notificationManager?.createNotificationChannel(notificationChannel)

        startForeground(1, notification)
        println("Note")

        scope.launch {
            repeat(10) {
                delay(1500)
                progress(it)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("MyFirstService onStartCommand")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = myBinder

    override fun onDestroy() {
        println("MyFirstService onDestroy")
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        fun getService() = this@MyFirstService
    }
}