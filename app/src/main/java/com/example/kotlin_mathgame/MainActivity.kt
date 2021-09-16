package com.example.kotlin_mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var fadeZoomInLong : Animation
    lateinit var fadeDown : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var game1 = findViewById(R.id.btngame1) as Button
        var game2 = findViewById(R.id.btngame2) as Button
        var imgBtnGame1 = findViewById(R.id.imgBtnGame1) as ImageButton
        var textView = findViewById(R.id.textView) as TextView

        fadeZoomInLong = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in_long)
        fadeDown = AnimationUtils.loadAnimation(this, R.anim.fade_down)

        // Animation
        textView.startAnimation(fadeDown)
        game1.startAnimation(fadeZoomInLong)
        game2.startAnimation(fadeZoomInLong)
        imgBtnGame1.startAnimation(fadeZoomInLong)

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