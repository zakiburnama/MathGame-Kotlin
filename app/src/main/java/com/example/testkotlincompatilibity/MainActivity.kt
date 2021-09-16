package com.example.testkotlincompatilibity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.println
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import java.sql.DriverManager.println
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var resultImageView: ImageView

    lateinit var answerTextView: TextView

    lateinit var leftTopButton: Button
    lateinit var rightTopButton: Button
    lateinit var leftBotButton: Button
    lateinit var rightBotButton: Button

    var answer : Int = 0

    var remain : Int = 0

    var isFirstButtonPressed: Boolean = false;

    lateinit var animatorSet: AnimatorSet

    lateinit var fadeInAnimation : Animation
    lateinit var fadeOutAnimation : Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultImageView = findViewById(R.id.resultImageView)

        answerTextView = findViewById(R.id.answerTextView)

        leftTopButton = findViewById(R.id.leftTopButton)
        rightTopButton = findViewById(R.id.rightTopButton)
        leftBotButton = findViewById(R.id.leftBotButton)
        rightBotButton = findViewById(R.id.rightBotButton)

        generateQuestion()



        /*

        resultImageView.alpha = 0.0f
        resultImageView.scaleX = 0.75f
        resultImageView.scaleY = 0.75f

        val resultFadeIn = ObjectAnimator.ofFloat(resultImageView, "alpha", 1.0f).apply()
        {
            duration = 1000
        }
        val resultFadeOut = ObjectAnimator.ofFloat(resultImageView, "alpha", 0.0f).apply()
        {
            duration = 2000
        }

        val resultZoomInX = ObjectAnimator.ofFloat(resultImageView, "scaleX", 1.0f).apply()
        {
            duration = 100
        }
        val resultZoomInY = ObjectAnimator.ofFloat(resultImageView, "scaleY", 1.0f).apply()
        {
            duration = 100
        }

        animatorSet = AnimatorSet().apply() {
            play(resultFadeIn).before(resultFadeOut)
            play(resultFadeIn).with(resultZoomInX)
            play(resultFadeIn).with(resultZoomInY)
        }

         */

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        //

        resultImageView.setImageResource(R.drawable.icon_false)
        resultImageView.startAnimation(fadeInAnimation)

    }

    fun generateQuestion()
    {

        leftTopButton.isEnabled = true
        rightTopButton.isEnabled = true
        leftBotButton.isEnabled = true
        rightBotButton.isEnabled = true

        isFirstButtonPressed = false;

        fun IntRange.random() =
            Random().nextInt((endInclusive) - start) + start

        answer = (10..50).random()

        val option1 = (1..answer).random()
        val option2 = answer - option1

        val incorrect1 = (1..answer).random()
        val incorrect2 = (1..answer).random()

        val valueList = ArrayList<Int>();
        valueList.add(option1)
        valueList.add(option2)
        valueList.add(incorrect1)
        valueList.add(incorrect2)

        System.out.println(answer.toString() + " " + option1 + " " + option2);

        var randomValue = (0..valueList.size).random()

        System.out.println("Random value " + randomValue);

        var currentValue = valueList.removeAt(randomValue)
        System.out.println("Current value " + currentValue);

        leftTopButton.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        rightTopButton.text = currentValue.toString()

        randomValue = (0..valueList.size).random()
        currentValue = valueList.removeAt(randomValue)
        leftBotButton.text = currentValue.toString()

        currentValue = valueList.removeAt(0)
        rightBotButton.text = currentValue.toString()

        answerTextView.text = answer.toString();

        remain = answer
    }

    fun buttonPressed(v: View)
    {
        var button:Button = v as Button;

        val pressedValue = Integer.parseInt(button.text.toString())

        remain = remain - pressedValue

        System.out.println(pressedValue.toString() + " " +  remain.toString())

        if (isFirstButtonPressed)
        {
            // if 2 button is pressed, validate
            if (remain == 0)
            {
                System.out.println("CORRECT!!!")

                resultImageView.setImageResource(R.drawable.icon_true)
                //animatorSet.start()
                resultImageView.startAnimation(fadeInAnimation)



            }
            else
            {
                System.out.println("INCORRECT!!!")

                //val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                resultImageView.setImageResource(R.drawable.icon_false)
                //animatorSet.start()
                resultImageView.startAnimation(fadeOutAnimation)
            }
            generateQuestion()
        }
        else
        {
            // if 1 button is pressed, wait for second answer
            isFirstButtonPressed = true
            v.isEnabled = false
        }
    }
}