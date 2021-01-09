package net.isspgj.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * Created by pgj on 2021/1/9
 **/
object PStatusUtil {
    fun setTransparentStatus(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val flag = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            activity.window?.decorView?.systemUiVisibility = flag
            activity.window?.statusBarColor = Color.TRANSPARENT
        } else {
            activity.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }
}