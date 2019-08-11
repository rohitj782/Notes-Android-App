package com.rohitrj.notesapp.ui.notifications

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest

const val DESC_KEY = "DESC"

class MyNotificationManager {

    var data: Data = Data.Builder()
        .putString(DESC_KEY,"We love what you can store in us.")
        .build()

    var constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()


    var oneTimeWorkReq = OneTimeWorkRequest
        .Builder(MyWorker::class.java)
        .setInputData(data)
        .setConstraints(constraints)
        .build()
}