package com.example.mobileapp.adapters

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

@Deprecated("Custom OnClickListener used in adapter")
internal open class ActionListener(c: Context?) : OnTouchListener {
    private val gestureDetector: GestureDetector
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }
    private inner class GestureListener : SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            onLongClick()
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            onDoubleTap()
            return super.onDoubleTap(e)
        }
    }

    open fun onLongClick() {}
    open fun onDoubleTap() {}
    init {
        gestureDetector = GestureDetector(c, GestureListener())
    }
}