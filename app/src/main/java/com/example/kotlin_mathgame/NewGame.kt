package com.example.kotlin_mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList

class NewGame : AppCompatActivity()
{
    lateinit var betulSalahView: ImageView
    lateinit var judul: TextView
    lateinit var frameBawah : FrameLayout

    lateinit var btnOpsiA: Button
    lateinit var btnOpsiB: Button
    lateinit var btnOpsiC: Button
    lateinit var btnOpsiD: Button

    lateinit var fadeZoomIn : Animation
    lateinit var fadeZoomInLong : Animation
    lateinit var fadeZoomOut : Animation
    lateinit var fadeZoomOutLong : Animation
    lateinit var fadeZoomOutShort : Animation
    lateinit var fadeDown : Animation

    var answer : Int = 0
    var remain : Int = 0
    var btnflag : Int = 3
    var isFirstButtonPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        betulSalahView = findViewById(R.id.betulSalahView)
        judul = findViewById(R.id.judul)
        frameBawah = findViewById(R.id.frameBawah)


        btnOpsiA = findViewById(R.id.btnOpsiA)
        btnOpsiB = findViewById(R.id.btnOpsiB)
        btnOpsiC = findViewById(R.id.btnOpsiC)
        btnOpsiD = findViewById(R.id.btnOpsiD)

        fadeZoomIn = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in)
        fadeZoomInLong = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_in_long)
        fadeZoomOut = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_out)
        fadeZoomOutLong = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_out_long)
        fadeZoomOutShort = AnimationUtils.loadAnimation(this, R.anim.fade_zoom_out_short)
        fadeDown = AnimationUtils.loadAnimation(this, R.anim.fade_down)

        // Animation
        betulSalahView.setImageResource(R.drawable.ic_none)
        betulSalahView.startAnimation(fadeZoomOut)
        judul.startAnimation(fadeDown)
        frameBawah.startAnimation(fadeZoomIn)

        generateQuestion()
    }

    fun generateQuestion()
    {
        // Button enable
        btnOpsiA.isEnabled = true
        btnOpsiB.isEnabled = true
        btnOpsiC.isEnabled = true
        btnOpsiD.isEnabled = true

        isFirstButtonPressed = false

        // Btn icon
        val drawable = ContextCompat.getDrawable(this@NewGame, R.drawable.ic_avatar)
        btnOpsiA.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
        btnOpsiB.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
        btnOpsiC.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
        btnOpsiD.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)

        // Animation option button
        btnOpsiA.startAnimation(fadeZoomInLong)
        btnOpsiB.startAnimation(fadeZoomInLong)
        btnOpsiC.startAnimation(fadeZoomInLong)
        btnOpsiD.startAnimation(fadeZoomInLong)

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
            judul.text = answer.toString()
        }
        else if (Flag == "B"){
            option4 = answer - option3
            // judul.text = ""
        }

        val valueList = ArrayList<Int>()
        valueList.add(option1)
        valueList.add(option2)
        valueList.add(option3)
        valueList.add(option4)

        var randomValue = (0..valueList.size).random()
        var currentValue = valueList.removeAt(randomValue)
        btnOpsiA.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnOpsiB.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnOpsiC.text = currentValue.toString()

        currentValue = valueList.removeAt(0)
        btnOpsiD.text = currentValue.toString()

        remain = answer
    }

    fun buttonPressed(v: View)
    {
        var button:Button = v as Button

        // Nilai int btn yang ditekan
        val pressedValue = Integer.parseInt(button.text.toString())
        remain -= pressedValue

        // change button img
        val drawable = ContextCompat.getDrawable(this@NewGame, R.drawable.ic_avatar)
        val drawable2 = ContextCompat.getDrawable(this@NewGame, R.drawable.ic_idea)
        val drawabletrue = ContextCompat.getDrawable(this@NewGame, R.drawable.ic_resource_true)
        val drawablefalse = ContextCompat.getDrawable(this@NewGame, R.drawable.ic_resource_false)

        if (isFirstButtonPressed)
        {
            if (remain == 0)
            {
                betulSalahView.setImageResource(R.drawable.ic_check)
                betulSalahView.startAnimation(fadeZoomOut)

                // frameBawah.startAnimation(fadeZoomInLong)

                // Button Animation
                btnOpsiA.startAnimation(fadeZoomOutLong)
                btnOpsiB.startAnimation(fadeZoomOutLong)
                btnOpsiC.startAnimation(fadeZoomOutLong)
                btnOpsiD.startAnimation(fadeZoomOutLong)

                btnflag = 3

                Handler().postDelayed({
                    generateQuestion()
                },1000)

            }
            else
            {
                remain = answer
                isFirstButtonPressed = false
                btnOpsiA.isEnabled = true
                btnOpsiB.isEnabled = true
                btnOpsiC.isEnabled = true
                btnOpsiD.isEnabled = true

                betulSalahView.setImageResource(R.drawable.ic_delete)
                betulSalahView.startAnimation(fadeZoomOut)

                btnflag -= 1

                if (btnflag == 0){

                    // Nilai int semua btn
                    var nilaiA = pressedValue + Integer.parseInt(btnOpsiA.text.toString())
                    var nilaiB = pressedValue + Integer.parseInt(btnOpsiB.text.toString())
                    var nilaiC = pressedValue + Integer.parseInt(btnOpsiC.text.toString())
                    var nilaiD = pressedValue + Integer.parseInt(btnOpsiD.text.toString())

                    if (nilaiA == answer){
                        btnOpsiA.setCompoundDrawablesWithIntrinsicBounds(null, drawabletrue, null, null)
                        btnOpsiA.startAnimation(fadeZoomOutLong)
                    }else{
                        btnOpsiA.setCompoundDrawablesWithIntrinsicBounds(null, drawablefalse, null, null)
                        btnOpsiA.startAnimation(fadeZoomOutShort)
                    }
                    if (nilaiB == answer){
                        btnOpsiB.setCompoundDrawablesWithIntrinsicBounds(null, drawabletrue, null, null)
                        btnOpsiB.startAnimation(fadeZoomOutLong)
                    }else{
                        btnOpsiB.setCompoundDrawablesWithIntrinsicBounds(null, drawablefalse, null, null)
                        btnOpsiB.startAnimation(fadeZoomOutShort)
                    }
                    if (nilaiC == answer){
                        btnOpsiC.setCompoundDrawablesWithIntrinsicBounds(null, drawabletrue, null, null)
                        btnOpsiC.startAnimation(fadeZoomOutLong)
                    }else{
                        btnOpsiC.setCompoundDrawablesWithIntrinsicBounds(null, drawablefalse, null, null)
                        btnOpsiC.startAnimation(fadeZoomOutShort)
                    }
                    if (nilaiD == answer){
                        btnOpsiD.setCompoundDrawablesWithIntrinsicBounds(null, drawabletrue, null, null)
                        btnOpsiD.startAnimation(fadeZoomOutLong)
                    }else{
                        btnOpsiD.setCompoundDrawablesWithIntrinsicBounds(null, drawablefalse, null, null)
                        btnOpsiD.startAnimation(fadeZoomOutShort)
                    }
                    v.setCompoundDrawablesWithIntrinsicBounds(null, drawabletrue, null, null)
                    v.startAnimation(fadeZoomOutLong)

                    btnflag = 3

                    Handler().postDelayed({
                        generateQuestion()
                    },1000)

                }
                else{
                    // Change icon back
                    btnOpsiA.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                    btnOpsiB.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                    btnOpsiC.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                    btnOpsiD.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)

                    // Button Animation
                    btnOpsiA.startAnimation(fadeZoomIn)
                    btnOpsiB.startAnimation(fadeZoomIn)
                    btnOpsiC.startAnimation(fadeZoomIn)
                    btnOpsiD.startAnimation(fadeZoomIn)

                }
            }
        }
        else
        {
            isFirstButtonPressed = true
            v.isEnabled = false
            v.setCompoundDrawablesWithIntrinsicBounds(null, drawable2, null, null)
            v.startAnimation(fadeZoomOutShort)
        }
    }
}