package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mStartActBtn = findViewById<Button>(R.id.barcodeu)



        mStartActBtn.setOnClickListener {
            //start activity intent
            startActivity(Intent(this@MainActivity, BarcodeActivity::class.java))
        }

        fun onSelectDas(v: View?) {
            println("Das Button")
        }


        fun _setVisibility() {}

        fun _setConstraints() {}
    }
}