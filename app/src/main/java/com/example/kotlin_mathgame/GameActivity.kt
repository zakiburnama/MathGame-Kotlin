package com.example.kotlin_mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity()
{

    lateinit var header: TextView
    lateinit var header2: TextView

    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var btnC: Button
    lateinit var btnD: Button

    var answer : Int = 0
    var remain : Int = 0
    var isFirstButtonPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        header = findViewById(R.id.header)
        header2 = findViewById(R.id.header2)

        btnA = findViewById(R.id.btnA)
        btnB = findViewById(R.id.btnB)
        btnC = findViewById(R.id.btnC)
        btnD = findViewById(R.id.btnD)

        generateQuestion()
    }

    fun generateQuestion()
    {
        btnA.isEnabled = true
        btnB.isEnabled = true
        btnC.isEnabled = true
        btnD.isEnabled = true

        isFirstButtonPressed = false


        var Flag = intent.getStringExtra("flag")
        System.out.println("Nilai Flag : " + Flag) //

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
        // System.out.println("Opsi 4 " + option4)

        val valueList = ArrayList<Int>()
        valueList.add(option1)
        valueList.add(option2)
        valueList.add(option3)
        valueList.add(option4)

        System.out.println(answer.toString() + " " + option1 + " " + option2) //

        var randomValue = (0..valueList.size).random()
        System.out.println("Random value " + randomValue) //
        var currentValue = valueList.removeAt(randomValue)
        System.out.println("Current value " + currentValue) //
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

        System.out.println(pressedValue.toString() + " " +  remain.toString()) //

        if (isFirstButtonPressed)
        {
            if (remain == 0)
            {
                System.out.println("CORRECT!!!")
                header.text = "BENAR"
                generateQuestion()
            }
            else
            {
                System.out.println("INCORRECT!!!")
                header.text = "SALAH"
                remain = answer
                isFirstButtonPressed = false
                btnA.isEnabled = true
                btnB.isEnabled = true
                btnC.isEnabled = true
                btnD.isEnabled = true
            }
        }
        else
        {
            isFirstButtonPressed = true
            v.isEnabled = false
        }
    }

}