package com.example.a211227_timer

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {

    lateinit var np1 : NumberPicker
    lateinit var np2 : NumberPicker

    lateinit var b1 : Button
    lateinit var b2 : Button

    lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        np1 = findViewById(R.id.np1)
        np2 = findViewById(R.id.np2)

        np1.minValue = 0
        np1.maxValue = 59
        np2.minValue = 0
        np2.maxValue = 59

        b1 = findViewById(R.id.start)
        b2 = findViewById(R.id.reset)

        b1.setOnClickListener() {

            if(b1.text == "시작") {

                np2.value += 1

                b1.text = "정지"

                var delayCount = 1

                /*var wholeSec = np1.value * 60 + np2.value

                timer = object : CountDownTimer((wholeSec * 1000).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                            np1.value = millisUntilFinished.toInt() / 60000
                            np2.value = (millisUntilFinished.toInt() % 60000) / 1000
                    }*/


                timer = object : CountDownTimer((np1.value * 60000 + np2.value * 1000 + 1000).toLong(), 1000) {

                        override fun onTick(millisUntilFinished: Long) {

                            println(millisUntilFinished)

//                            if (delayCount > 0){
//                                delayCount--
//                            } else{

                                if (np1.value != 0 && np2.value == 0) {
                                    np1.value -= 1
                                    np2.value = 59
                                }else{
                                    np2.value -= 1
                                }
//                           }
                        }

                        override fun onFinish() {
                            b1.text = "시작"
                        }
                    }

                    timer.start()

            } else {
                b1.text="시작"

                timer.cancel()
            }
        }

        b2.setOnClickListener() {
            b1.text = "시작"
            np1.value = 0
            np2.value = 0
            timer.cancel()
        }
    }
}