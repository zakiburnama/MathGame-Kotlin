package com.example.kotlin_mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var game1 = findViewById(R.id.btngame1) as Button
        var game2 = findViewById(R.id.btngame2) as Button

        //
        game1.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("flag", "A")
            startActivity(intent)
        }
        //
        game2.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("flag", "B")
            startActivity(intent)
        }
    }
}