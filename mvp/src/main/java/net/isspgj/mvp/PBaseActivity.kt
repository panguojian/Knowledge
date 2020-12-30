package net.isspgj.mvp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by pgj on 2020/12/29
 **/
abstract class PBaseActivity<T : PIPresenter> : AppCompatActivity(), PIView {
    protected var mPresenter: T? = null
    abstract fun setPresenter(): T
    abstract fun setLayoutId(): Int
    abstract fun initial()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        mPresenter = setPresenter()
        initial()
    }

    override fun onDestroy() {
        mPresenter?.onRelease()
        super.onDestroy()
    }

    fun showTips(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showTips(msg: Int) {
        showTips(getString(msg))
    }
}