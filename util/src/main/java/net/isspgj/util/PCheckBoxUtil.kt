package net.isspgj.util

import android.view.ViewGroup
import android.widget.CheckBox

/**
 * Created by pgj on 2020/12/29
 **/
object PCheckBoxUtil {
    fun getString(viewGroup: ViewGroup): String {
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            if (view is CheckBox && view.isChecked) {
                return view.text.toString()
            }
        }
        return ""
    }
}