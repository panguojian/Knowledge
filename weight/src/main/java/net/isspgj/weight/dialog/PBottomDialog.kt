package net.isspgj.weight.dialog

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager

/**
 * Created by pgj on 2020/12/30
 **/

class PBottomDialog : PBaseBottomDialog() {


    companion object {
        const val KEY_LAYOUT_RES = "bottom_layout_res"
        const val KEY_CANCEL_OUTSIDE = "bottom_cancel_outside"
        fun build(manager: FragmentManager): PBottomDialog {
            val bottomDialog = PBottomDialog()
            bottomDialog.setFragmentManager(manager)
            return bottomDialog
        }
    }

    private var mFragmentManager: FragmentManager? = null

    private var mIsCancelOutside = super.setCancelOutside()



    @LayoutRes
    private var mLayoutRes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            mLayoutRes = getInt(KEY_LAYOUT_RES)
            mIsCancelOutside = getBoolean(KEY_CANCEL_OUTSIDE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes)
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside)
        super.onSaveInstanceState(outState)
    }

    override fun getLayoutRes(): Int {
        return mLayoutRes
    }

    private var mViewListener: ViewListener? = null

    override fun bindView(v: View) {
        mViewListener?.bindView(v)
    }

    fun setFragmentManager(manager: FragmentManager): PBottomDialog? {
        mFragmentManager = manager
        return this
    }

    fun setViewListener(listener: ViewListener): PBottomDialog? {
        mViewListener = listener
        return this
    }

    fun setLayoutRes(@LayoutRes layoutRes: Int): PBottomDialog? {
        mLayoutRes = layoutRes
        return this
    }

    fun setCancelOutside(cancel: Boolean): PBottomDialog? {
        mIsCancelOutside = cancel
        return this
    }

    interface ViewListener {
        fun bindView(v: View?)
    }

    fun show(): PBottomDialog? {
        show(mFragmentManager!!)
        return this
    }
}