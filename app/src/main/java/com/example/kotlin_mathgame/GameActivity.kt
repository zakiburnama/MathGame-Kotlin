package com.example.kotlin_mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.println
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.sql.DriverManager.println
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    lateinit var answerTextView: TextView

    lateinit var btnRightTop: Button
    lateinit var btnLeftTop: Button
    lateinit var btnRightBottom: Button
    lateinit var btnLeftBottom: Button

    var answer : Int = 0
    var remain : Int = 0
    var isFirstButtonPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        answerTextView = findViewById(R.id.answerTextView)

        btnRightTop = findViewById(R.id.btnRightTop)
        btnLeftTop = findViewById(R.id.btnLeftTop)
        btnRightBottom = findViewById(R.id.btnRightBottom)
        btnLeftBottom = findViewById(R.id.btnLeftBottom)



        generateQuestion()

    }

    fun generateQuestion()
    {
        btnRightTop.isEnabled = true
        btnLeftTop.isEnabled = true
        btnRightBottom.isEnabled = true
        btnLeftBottom.isEnabled = true

        isFirstButtonPressed = false

        var Flag = intent.getStringExtra("flag")
        //System.out.println("Sesuatu " + Flag)


        fun IntRange.random() =
            Random().nextInt((endInclusive) - start) + start

        //answer = (10..50).random()

        //val option1 = (1..answer).random()
        //val option2 = answer - option1

        val option1 = (10..50).random()
        val option2 = (10..50).random()

        answer = option1 + option2

        //val incorrect1 = (1..answer).random()
        //val incorrect2 = (1..answer).random()

        val option3 = (10..answer).random()

        var option4 = 0

        if (Flag == "A"){
            option4 = (10..answer).random()
            answerTextView.text = answer.toString();
        }
        else if (Flag == "B"){
            option4 = answer - option3
        }

        System.out.println("Opsi 4 " + option4)

        val valueList = ArrayList<Int>()
        valueList.add(option1)
        valueList.add(option2)
        valueList.add(option3)
        valueList.add(option4)

        System.out.println(answer.toString() + " " + option1 + " " + option2)

        var randomValue = (0..valueList.size).random()

        System.out.println("Random value " + randomValue)

        var currentValue = valueList.removeAt(randomValue)
        System.out.println("Current value " + currentValue)

        btnRightTop.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnLeftTop.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        btnRightBottom.text = currentValue.toString()

        currentValue = valueList.removeAt(0)
        btnLeftBottom.text = currentValue.toString()

        remain = answer
    }

    fun buttonPressed(v: View)
    {
        var button:Button = v as Button

        val pressedValue = Integer.parseInt(button.text.toString())

        remain = remain - pressedValue

        System.out.println(pressedValue.toString() + " " +  remain.toString())

        if (isFirstButtonPressed)
        {
            // if 2 button is pressed, validate
            if (remain == 0)
            {
                System.out.println("CORRECT!!!")
            }
            else
            {
                System.out.println("INCORRECT!!!")
            }
            generateQuestion()
        }
        else
        {
            isFirstButtonPressed = true
            v.isEnabled = false
        }
    }

}