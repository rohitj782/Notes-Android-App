package com.rohitrj.notesapp.internals.utlity

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(applicationContext,message,length).show()
}