package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollButton)
        val resultsTextView = findViewById<TextView>(R.id.resultsText)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val mStartActBtn = findViewById<Button>(R.id.barcodeu)

        rollButton.setOnClickListener {
            val rand = java.util.Random().nextInt(seekBar.progress)
            resultsTextView.text =rand.toString()

        }


        mStartActBtn.setOnClickListener {
            //start activity intent
            startActivity(Intent(this@MainActivity, BarcodeActivity::class.java))
        }
    }
}