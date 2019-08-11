package com.rohitrj.notesapp.ui.notifications

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import androidx.work.WorkManager

@SuppressLint("Registered")
class MyJobService: JobService() {
    override fun onStopJob(p0: JobParameters?): Boolean { return false }

    override fun onStartJob(p0: JobParameters?): Boolean {
        WorkManager.getInstance().enqueue(MyNotificationManager().oneTimeWorkReq)
        return false
        }
}