package com.example.kotlin_mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    lateinit var fadeZoomIn : Animation
    lateinit var fadeZoomOut : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var game1 = findViewById(R.id.btngame1) as Button
        var game2 = findViewById(R.id.btngame2) as Button
        var imgBtnGame1 = findViewById(R.id.imgBtnGame1) as ImageButton

        fadeZoomIn = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in)
        fadeZoomOut = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_out)

        imgBtnGame1.startAnimation(fadeZoomIn)
        game1.startAnimation(fadeZoomIn)

        // Klik button img game 1
        imgBtnGame1.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("flag", "A") // mengirim nilai flag ke activity 2
            startActivity(intent)
        }
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