package com.example.myapplication

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.v("___", "$intent")
        Toast.makeText(context, "$intent", Toast.LENGTH_LONG).show()
    }
}

object NotificationController {
    fun pendingIntent(context: Context, name: Action, extras: Bundle?): PendingIntent {
        val intent = Intent(context, NotificationBroadcastReceiver::class.java).apply {
            action = name.toString()
            extras?.let { putExtra(EXTRA_NOTIFICATION_ID, it) }
        }

        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    enum class Action
    {
        ACTION_1,
        ACTION_2,
    }
}