package net.isspgj.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pgj on 2020/12/29
 **/
object PTimeUtil {
    fun getCurrent():String{
        val format = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        return format.format(Date())
    }

    fun getCurrent(format:String):String{
        val format = SimpleDateFormat(format)
        return format.format(Date())
    }
}