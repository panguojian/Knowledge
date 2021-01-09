package net.isspgj.knowledge.util


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.isspgj.knowledge.R
import net.isspgj.util.PStatusUtil

class UtilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_util)
        PStatusUtil.setTransparentStatus(this)
    }
}