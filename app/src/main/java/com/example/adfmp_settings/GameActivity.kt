package com.example.adfmp_settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide() ?: Log.d("MANUAL","No ActionBar")
        findViewById<View>(R.id.buttonConcede).setOnClickListener { this.finish() }
        findViewById<View>(R.id.buttonHome).setOnClickListener { this.finish() }
    }
}