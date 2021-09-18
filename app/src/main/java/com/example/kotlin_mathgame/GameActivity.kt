package com.example.kotlin_mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity()
{
    lateinit var imageView: ImageView

    lateinit var header: TextView
    lateinit var header2: TextView

    lateinit var frameBtn : FrameLayout

    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var btnC: Button
    lateinit var btnD: Button

    var answer : Int = 0
    var remain : Int = 0
    var isFirstButtonPressed: Boolean = false

    lateinit var fadeZoomIn : Animation
    lateinit var fadeZoomOut : Animation
    lateinit var fadeZoomInLong : Animation
    lateinit var fadeDown : Animation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageView = findViewById(R.id.imageView)

        header = findViewById(R.id.judul)
        header2 = findViewById(R.id.header2)

        frameBtn = findViewById(R.id.frameBawah)

        btnA = findViewById(R.id.btnOpsiA)
        btnB = findViewById(R.id.btnOpsiB)
        btnC = findViewById(R.id.btnOpsiC)
        btnD = findViewById(R.id.btnOpsiD)

        fadeZoomIn = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in)
        fadeZoomOut = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_out)
        fadeZoomInLong = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in_long)
        fadeDown = AnimationUtils.loadAnimation(this, R.anim.fade_down)

        imageView.setImageResource(R.drawable.ic_none)
        imageView.startAnimation(fadeZoomOut)
        frameBtn.startAnimation(fadeZoomIn)
        header.startAnimation(fadeDown)
        header2.startAnimation(fadeDown)

        btnA.startAnimation(fadeZoomInLong)
        btnB.startAnimation(fadeZoomInLong)
        btnC.startAnimation(fadeZoomInLong)
        btnD.startAnimation(fadeZoomInLong)

        generateQuestion()

    }

    fun generateQuestion()
    {
        // Button enable
        btnA.isEnabled = true
        btnB.isEnabled = true
        btnC.isEnabled = true
        btnD.isEnabled = true

        isFirstButtonPressed = false


        var Flag = intent.getStringExtra("flag")

        fun IntRange.random() =
            Random().nextInt((endInclusive) - start) + start

        val option1 = (10..50).random()
        val option2 = (10..50).random()

        answer = option1 + option2

        val option3 = (10..answer).random()
        var option4 = 0
        // percabangan untuk game 1 dan game 2
        if (Flag == "A"){
            option4 = (10..answer).random()
            header2.text = answer.toString()
        }
        else if (Flag == "B"){
            option4 = answer - option3
            header2.text = ""
        }

        val valueList = ArrayList<Int>()
        valueList.add(option1)
        valueList.add(option2)
        valueList.add(option3)
        valueList.add(option4)

        var randomValue = (0..valueList.size).random()
        var currentValue = valueList.removeAt(randomValue)
        btnA.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnB.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnC.text = currentValue.toString()

        currentValue = valueList.removeAt(0)
        btnD.text = currentValue.toString()

        remain = answer
    }

    fun buttonPressed(v: View)
    {
        var button:Button = v as Button

        val pressedValue = Integer.parseInt(button.text.toString())

        remain = remain - pressedValue

        if (isFirstButtonPressed)
        {
            if (remain == 0)
            {
                // header.text = "BENAR"

                imageView.setImageResource(R.drawable.ic_check)
                imageView.startAnimation(fadeZoomOut)

                frameBtn.startAnimation(fadeZoomInLong)

                // Button Animation
                btnA.startAnimation(fadeZoomIn)
                btnB.startAnimation(fadeZoomIn)
                btnC.startAnimation(fadeZoomIn)
                btnD.startAnimation(fadeZoomIn)

                generateQuestion()
            }
            else
            {
                // header.text = "SALAH"
                remain = answer
                isFirstButtonPressed = false
                btnA.isEnabled = true
                btnB.isEnabled = true
                btnC.isEnabled = true
                btnD.isEnabled = true

                imageView.setImageResource(R.drawable.ic_delete)
                imageView.startAnimation(fadeZoomOut)

                // Button Animation
                btnA.startAnimation(fadeZoomIn)
                btnB.startAnimation(fadeZoomIn)
                btnC.startAnimation(fadeZoomIn)
                btnD.startAnimation(fadeZoomIn)
            }
        }
        else
        {
            isFirstButtonPressed = true
            v.isEnabled = false
            v.startAnimation(fadeZoomOut)
        }
    }

}