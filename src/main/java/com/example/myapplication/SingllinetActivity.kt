package com.example.myapplication

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.widget.Button


class SingllinetActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.singllinet_main)

        val mStartActBtn2 = findViewById<Button>(R.id.button_singllinet)

        mStartActBtn2.setOnClickListener {
            //start activity intent
            startActivity(Intent(this@SingllinetActivity, TimeActivity::class.java))
        }
    }
}