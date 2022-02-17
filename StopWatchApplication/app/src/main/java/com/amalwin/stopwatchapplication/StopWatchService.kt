package com.amalwin.stopwatchapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class StopWatchService : Service() {
    private val timer = Timer()
    init {
        Log.i(TAG, "Service created in the memory !")
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service is running !")
        val currentTime = intent.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.scheduleAtFixedRate(stopWatchTimerTask(currentTime), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        Log.i(TAG, "Service is destroyed !")
    }

    companion object {
        const val TAG = "MYTAG"
        const val CURRENT_TIME = "Current Time"
        const val UPDATED_TIME = "Updated Time"
    }

    private inner class stopWatchTimerTask(private var currentTime: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(UPDATED_TIME)
            currentTime++
            intent.putExtra(UPDATED_TIME, currentTime)
            sendBroadcast(intent)
        }
    }

}