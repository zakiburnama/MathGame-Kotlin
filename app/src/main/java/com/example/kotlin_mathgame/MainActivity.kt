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

        // Klik button game 1
        game1.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("flag", "A") // mengirim nilai flag ke activity 2
            startActivity(intent)
        }
        // Klik button game 2
        game2.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("flag", "B") // mengirim nilai flag ke activity 2
            startActivity(intent)
        }
    }
}