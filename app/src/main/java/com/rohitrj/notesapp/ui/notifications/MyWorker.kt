package com.rohitrj.notesapp.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.ui.MainActivity
import com.rohitrj.notesapp.ui.notes.allnotes.AllNotesFragmentDirections

const val CHANNEL_ID = "NOTES_ID"
const val CHANNEL_NAME = "NOTES"

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val data = inputData
        val desc = data.getString(DESC_KEY)
        displayNotification("Keep Writing...", desc!!)
        return Result.success()
    }

    private fun displayNotification(task: String, desc: String) {

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //create notification channel

            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(notificationChannel)

        }

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val builder = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setContentTitle(task)
            .setContentText(desc)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher_round)

        notificationManager.notify(1, builder.build())


    }
}