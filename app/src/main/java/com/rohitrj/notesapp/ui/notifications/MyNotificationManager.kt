package com.rohitrj.notesapp.ui.notifications

import androidx.work.OneTimeWorkRequest

class MyNotificationManager {

    var oneTimeWorkReq = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
}