package com.vogella.android.recognizinggestures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var state = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myView.setOnTouchListener { v: View, m:MotionEvent ->
            handleTouch(m)
            true
        }

    }
    private fun handleTouch(m:MotionEvent) {
        val pointerCount = m.pointerCount

        for (i in 0 until pointerCount) {
            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex
            var actionString: String


            when (action) {
                MotionEvent.ACTION_DOWN -> actionString = "DOWN"
                MotionEvent.ACTION_UP -> actionString = "UP"
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> actionString = "PNTR UP"
                MotionEvent.ACTION_MOVE -> actionString = "MOVE"

                else -> actionString = ""

            }
            val touchStatus = "Action: $actionString Index: $actionIndex ID: $id"
            if (actionIndex == 0) {
                textView1.text = touchStatus
                if (action == MotionEvent.ACTION_DOWN) {
                    state = 1

                    Log.d("Gestures", id.toString())
                }
                if (state == 2) {
                    if (action == MotionEvent.ACTION_POINTER_UP) {
                        state = 3
                        Log.d("Gestures", id.toString())
                    }
                }
                if (state == 3) {
                    if (action == MotionEvent.ACTION_POINTER_DOWN) {
                        Toast.makeText(this, "UNLOCKED", Toast.LENGTH_LONG).show()
                        Log.d("Gestures", "UNLOCKED")

                    }
                }
            }
            if (actionIndex == 1) {
                textView2.text = touchStatus
                if (action == MotionEvent.ACTION_POINTER_DOWN) {
                    if (state == 1) {
                        state = 2
                        Log.d("Gestures", id.toString())
                    }
                }
            }
        }
    }
}
