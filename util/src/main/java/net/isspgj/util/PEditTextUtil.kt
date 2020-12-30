package net.isspgj.util

import android.widget.EditText

/**
 * Created by pgj on 2020/12/29
 **/
object PEditTextUtil {
    /**
     * @param editText
     * @return 如果文本为空返回 true 否则 false
     */
    fun isEmpty(editText: EditText):Boolean{
        return editText.text.toString() == ""
    }
}