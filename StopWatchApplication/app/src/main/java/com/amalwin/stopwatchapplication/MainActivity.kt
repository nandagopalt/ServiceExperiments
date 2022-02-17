package com.amalwin.stopwatchapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amalwin.stopwatchapplication.StopWatchService.Companion.CURRENT_TIME
import com.amalwin.stopwatchapplication.StopWatchService.Companion.UPDATED_TIME
import com.amalwin.stopwatchapplication.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isServiceRunning = false
    private lateinit var serviceIntent: Intent
    private var currentTime = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(updateTimerBroadcastReceiver, IntentFilter(UPDATED_TIME))
        serviceIntent = Intent(applicationContext, StopWatchService::class.java)

        binding.startStopButton.setOnClickListener {
            startOrStopTimer()
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun startOrStopTimer() {
        if (isServiceRunning) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        binding.startStopButton.text = "Stop"
        isServiceRunning = true
        serviceIntent.putExtra(CURRENT_TIME, currentTime)
        startService(serviceIntent)
    }

    private fun stopTimer() {
        binding.startStopButton.text = "Start"
        isServiceRunning = false
        stopService(serviceIntent)
    }

    private fun resetTimer() {
        stopTimer()
        currentTime = 0.0
        binding.tvTimer.text = getFormattedTime(currentTime)
    }

    private val updateTimerBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            currentTime = intent?.getDoubleExtra(UPDATED_TIME, 0.0)!!
            binding.tvTimer.text = getFormattedTime(currentTime)
        }
    }

    private fun getFormattedTime(time: Double): String {
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}