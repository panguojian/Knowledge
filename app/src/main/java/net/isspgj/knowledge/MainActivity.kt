package net.isspgj.knowledge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.isspgj.util.PTimeUtil
import net.isspgj.weight.dialog.BottomDialog

class MainActivity : AppCompatActivity() {
    var bottomDialog: BottomDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            showBottomDialog()
        }
    }

    private fun showBottomDialog() {
        if (bottomDialog == null) {
            initBottomDialog()
        }
        bottomDialog?.show()
    }

    private fun initBottomDialog() {
        bottomDialog =
            BottomDialog.build(supportFragmentManager).setLayoutRes(R.layout.dialog_me)
                ?.setViewListener(object : BottomDialog.ViewListener {
                    override fun bindView(v: View?) {
                        v?.setOnClickListener {
                            Toast.makeText(this@MainActivity,PTimeUtil.getCurrent(),Toast.LENGTH_SHORT).show()
                            bottomDialog?.dismiss()
                        }
                    }

                })
    }

    private fun clearDialog(){
        bottomDialog?.dismiss()
        bottomDialog = null
    }

    override fun onDestroy() {
        clearDialog()
        super.onDestroy()
    }
}