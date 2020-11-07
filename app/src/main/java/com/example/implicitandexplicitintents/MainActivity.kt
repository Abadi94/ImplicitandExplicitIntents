package com.example.implicitandexplicitintents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener{btn1()}
        findViewById<Button>(R.id.button2).setOnClickListener{btn2()}
        findViewById<Button>(R.id.button3).setOnClickListener{btn3("message",4,20)}
    }
     private fun btn1(){
         val gmmIntentUri = Uri.parse("geo:14.5964957,120.9445402,13")
         val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
         mapIntent.setPackage("com.google.android.apps.maps")
         mapIntent.resolveActivity(packageManager)?.let {
             startActivity(mapIntent)
         }

     }

    private fun btn2() {
        val REQUEST_IMAGE_CAPTURE = 1
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {

        }

    }
    private fun btn3(message: String, hour: Int, minutes: Int){
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }
}

