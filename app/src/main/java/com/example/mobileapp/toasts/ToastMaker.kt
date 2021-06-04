package com.example.mobileapp.toasts

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import www.sanju.motiontoast.MotionToast

object ToastMaker {


    public fun makeSuccessToast(context : Activity, message : String) {
        MotionToast.darkColorToast(context,
            "Success",
            message,
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular))
    }


    public fun makeErrorToast(context: Activity, message: String) {
        MotionToast.darkColorToast(context,
            "Error",
            message,
            MotionToast.TOAST_ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular))
    }
}