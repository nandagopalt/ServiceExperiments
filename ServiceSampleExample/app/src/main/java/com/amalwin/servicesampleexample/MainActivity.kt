package com.amalwin.servicesampleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amalwin.servicesampleexample.MyBackgroundService.Companion.MARKS
import com.amalwin.servicesampleexample.MyBackgroundService.Companion.NAME
import com.amalwin.servicesampleexample.MyBackgroundService.Companion.TAG
import com.amalwin.servicesampleexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        serviceIntent.putExtra(NAME, "Alexandar")
        serviceIntent.putExtra(MARKS, 82)
        binding.startButton.setOnClickListener {
            Log.i(TAG, "Call for service creation !")
            startService(serviceIntent)
        }

        binding.stopButton.setOnClickListener {
            Log.i(TAG, "Call to destroy service !")
            stopService(serviceIntent)
        }
    }
}