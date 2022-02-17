package com.amalwin.servicesampleexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {
    override fun onBind(intent: Intent): IBinder? = null
    init {
        Log.i(TAG, "Service instantiated / created in the memory !")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service running !")
        val name = intent.getStringExtra(NAME)
        val marks = intent.getIntExtra(MARKS, 78)
        Log.i(TAG, "Name : $name and Marks : $marks")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Service destroyed !")
    }

    companion object {
        const val TAG = "MYTAG"
        const val NAME = "NAME"
        const val MARKS = "MARKS"
    }
}