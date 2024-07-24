package com.example.pillreminder

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val reminderText = intent?.getStringExtra("REMINDER_TEXT") ?: return


        // Intent for the full-screen activity
        val fullScreenIntent = Intent(context, ReminderFullScreen::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("REMINDER_TEXT", reminderText)
        }



        val pendingIntent = PendingIntent.getActivity(context, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Set the alarm sound
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        //Build the notification
        val builder = NotificationCompat.Builder(context!!, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Pill Reminder")
            .setContentText(reminderText)
            .setPriority(NotificationCompat.PRIORITY_HIGH)//High priority for heads-up notification
            .setFullScreenIntent(pendingIntent, true)// Full-screen intent
            .setSound(alarmSound)
            .setAutoCancel(true)

        //show the notification
        with(NotificationManagerCompat.from(context)) {
            notify(0, builder.build())
        }
        Log.d("ReminderReceiver", "Launching full-screen activity with text: $reminderText")

    }
}

