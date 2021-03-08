package net.isspgj.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pgj on 2020/12/29
 **/
/**
 * 一个时间工具类
 * 获取当前时间
 */
object PTimeUtil {
    /**
     * 返回当前时间 默认格式：2020/12/29 10:51:22
     */
    fun getCurrent():String{
        val format = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        return format.format(Date())
    }

    /**
     * 返回当前时间并指定时间格式：format
     * @format 格式形如 yyyy/MM/dd hh:mm:ss  yyyy-MM-dd  yyyy年MM月dd日  hh时mm分ss秒
     */
    fun getCurrent(format:String):String{
        val format = SimpleDateFormat(format)
        return format.format(Date())
    }
}