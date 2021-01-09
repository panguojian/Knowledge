package net.isspgj.util

import android.content.Context
import android.widget.Toast

/**
 * Created by pgj on 2020/12/30
 **/
object PToastUtil {
    fun showShort(context:Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun showLong(context:Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

    fun showShort(context: Context,msg:Int){
        showShort(context,context.getString(msg))
    }

    fun showLong(context: Context,msg:Int){
        showLong(context,context.getString(msg))
    }
}