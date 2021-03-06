package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            binding.textView.text = "dfghjkl"

            // Создаём уведомление
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Напоминание")
                .setContentText("Пора покормить кота")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(NotificationController.pendingIntent(applicationContext,
                    name = NotificationController.Action.ACTION_1,
                    extras = null))
                .addAction(R.drawable.ic_launcher_foreground, "vvvv",
                    NotificationController.pendingIntent(
                        this,
                        NotificationController.Action.ACTION_2,
                        null,
                    )
                )
            NotificationManagerCompat.from(applicationContext).run {
                if (Build.VERSION.SDK_INT >= 26) {
                    val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                    createNotificationChannel(notificationChannel)
                }
                notify(NOTIFICATION_ID, builder.build())
            }
        }

        binding.button2.setOnClickListener {
            NotificationManagerCompat.from(applicationContext).cancel(NOTIFICATION_ID)
        }
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
    }
}