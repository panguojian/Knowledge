package net.isspgj.util

import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * Created by pgj on 2020/12/29
 **/
object PRadioGroupUtil {
    /**
     * @param radioGroup
     * @return 返回 RadioGroup 中 被选中的  RadioButton 的文本
     */
    fun getString(radioGroup: RadioGroup): String {
        for (i in 0 until radioGroup.childCount) {
            val view = radioGroup.getChildAt(i)
            if (view is RadioButton && view.isChecked) {
                return view.text.toString()
            }
        }
        return ""
    }
}