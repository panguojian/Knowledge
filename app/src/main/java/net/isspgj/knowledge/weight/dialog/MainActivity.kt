package net.isspgj.knowledge.weight.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.isspgj.knowledge.R
import net.isspgj.weight.dialog.PBottomDialog

class MainActivity : AppCompatActivity() {
    var bottomDialog: PBottomDialog? = null
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
            PBottomDialog.build(supportFragmentManager).setLayoutRes(R.layout.dialog_me)
                ?.setViewListener(object : PBottomDialog.ViewListener {
                    override fun bindView(v: View?) {
                        v?.setOnClickListener {
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