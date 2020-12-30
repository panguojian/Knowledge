package net.isspgj.weight.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import net.isspgj.weight.R

/**
 * Created by pgj on 2020/12/30
 **/
abstract class BaseBottomDialog : DialogFragment() {

    abstract fun getLayoutRes():Int
    abstract fun bindView(v:View)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        dialog?.setCanceledOnTouchOutside(setCancelOutside())
        val view = inflater.inflate(getLayoutRes(),container,false)
        bindView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = Gravity.BOTTOM
        window?.attributes = params
    }

    open fun setCancelOutside() = true

    fun show(fragmentManager: FragmentManager){
        show(fragmentManager,"TAG")
    }
}