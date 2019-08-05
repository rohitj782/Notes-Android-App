package com.rohitrj.notesapp.ui.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.contentValuesOf
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rohitrj.notesapp.R

const val CHANNEL_ID = "NOTES_ID"
const val CHANNEL_NAME = "NOTES"

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        displayNotification("Title ", "Work is Finished")
        return Result.success()
    }

    private fun displayNotification(task: String, desc: String) {

        var notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //create notification channel

            var notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(notificationChannel)

        }

        var builder = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher_round)

        notificationManager.notify(1, builder.build())

    }
}