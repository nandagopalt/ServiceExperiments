package com.amalwin.servicesampleexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {
    override fun onBind(intent: Intent): IBinder? = null
    // Code block to be called when the class MyBackgroundService gets instantiated from the client
    init {
        Log.i(TAG, "Service instantiated / created in the memory !")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service running !")
        val name = intent.getStringExtra(NAME)
        val marks = intent.getIntExtra(MARKS, 78)
        Log.i(TAG, "Name : $name, Marks : $marks , startId: $startId")
        return START_STICKY // Android system may restart the service at the time of memory crunch or during the prioritization of the application execution and while
                            // restarting the flag indicates the android system whether to pass the intent. "START_STICKY" allows service to restart but not to pass the intent that was lastly executed.
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