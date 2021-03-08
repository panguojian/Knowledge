package net.isspgj.knowledge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_launcher.*
import net.isspgj.knowledge.chart.ChartActivity

class LauncherActivity : AppCompatActivity() {
    private val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        setData()
        setList()
        setEvents()
    }

    private fun setData(){
        list.add("Android图表运用示例")
    }

    private fun setList(){
        lv_intent.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list)
    }

    private fun setEvents(){
        lv_intent.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when(position){
                    0 -> {
                        val intent = Intent(this,ChartActivity::class.java)
                        openPage(intent)
                    }
                }
            }
    }

    private fun openPage(intent: Intent){
        startActivity(intent)
    }
}