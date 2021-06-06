package com.example.mobileapp.toasts

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import com.example.mobileapp.R
import www.sanju.motiontoast.MotionToast

object ToastMaker {


    fun makeSuccessToast(context : Activity, message : String) {
        MotionToast.darkColorToast(context,
            "Success",
            message,
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular))
    }


    fun makeErrorToast(context: Activity, message: String) {
        MotionToast.darkColorToast(context,
            "Error",
            message,
            MotionToast.TOAST_ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular))
    }

    fun makeInfoToast(context: Activity, message: String) {
        MotionToast.darkColorToast(context,"Warning",
                message,
                MotionToast.TOAST_INFO,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context,R.font.helvetica_regular))
    }
}