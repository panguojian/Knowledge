package net.isspgj.weight.tab

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.view.ViewCompat

/**
 * Created by pgj on 2021/1/5
 **/
class PTabBar : LinearLayout {
    private val mTabs = ArrayList<PTabItem>()

    lateinit var mTabLayout: LinearLayout
    private lateinit var mTabParams: LayoutParams
    var mCurrentPosition: Int = 0
    var mListener: OnTabSelectedListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        orientation = VERTICAL
        mTabLayout = LinearLayout(context)
        mTabLayout.orientation = HORIZONTAL
        addView(
            mTabLayout,
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        mTabParams = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
        mTabParams.weight = 1F
    }

    /**
     *  把PTabItem加入PTabBar
     */
    fun addItem(tab: PTabItem): PTabBar {
        tab.setOnClickListener {
            if (mListener == null) {
                return@setOnClickListener
            }
            val pos = tab.getTabPosition()
            if (mCurrentPosition == pos) {
                mListener?.onTabReselected(pos)
            } else {
                mListener?.onTabSelected(pos, mCurrentPosition)
                tab.isSelected = true
                mListener?.onTabUnselected(mCurrentPosition)
                mTabs[mCurrentPosition].isSelected = false
                mCurrentPosition = pos
            }
        }
        tab.setTabPosition(mTabLayout.childCount)
        tab.layoutParams = mTabParams
        mTabLayout.addView(tab)
        mTabs.add(tab)
        return this
    }

    /**
     *  设置底部导航栏子项的点击事件
     */
    fun setOnTabSelectedListener(onTabSelectedListener: OnTabSelectedListener) {
        mListener = onTabSelectedListener
    }

    /**
     *  post 方法干嘛用的
     */

    fun setCurrentItem(position: Int) {
        mTabLayout.post {
            mTabLayout.getChildAt(position).performClick()
        }
    }

    fun getCurrentItemPosition() = mCurrentPosition

    fun getItem(index: Int): PTabItem? {
        if (mTabs.size <= index || index < 0) return null
        return mTabs[index]
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int, prePosition: Int)
        fun onTabUnselected(position: Int)
        fun onTabReselected(position: Int)
    }

    /**
     *  手机配置发生更改（屏幕旋转等）会执行这个方法
     *  目的是恢复原来的状态
     */
    override fun onSaveInstanceState(): Parcelable? {
        Log.i("PTabBar", "onSaveInstanceState")
        val superState = super.onSaveInstanceState()
        return SavedState(superState!!, mCurrentPosition)
    }

    /**
     *  手机配置发生更改（屏幕旋转等）会执行这个方法
     *  目的是恢复原来的状态
     */
    override fun onRestoreInstanceState(state: Parcelable?) {
        Log.i("PTabBar", "onRestoreInstanceState")
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        if (mCurrentPosition != ss.position) {
            mTabLayout.getChildAt(mCurrentPosition).isSelected = false
            mTabLayout.getChildAt(ss.position).isSelected = true
        }
        mCurrentPosition = ss.position
    }

    class SavedState : BaseSavedState {
        var position: Int

        constructor(source: Parcel) : super(source) {
            position = source.readInt()
        }

        constructor(superState: Parcelable, position: Int) : super(superState) {
            this.position = position
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeInt(position)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel): SavedState {
                return SavedState(source)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls<SavedState>(size)
            }
        }
    }
}