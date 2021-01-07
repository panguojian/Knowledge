package net.isspgj.weight.tab

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import net.isspgj.weight.R
import java.lang.Exception

/**
 * Created by pgj on 2021/1/5
 **/

/**
 *  ----FrameLayout 在下面的注释中称为第一布局
 *  -------LinearLayout 在下面的注释中称为第二布局
 *  ----------ImageView 图标
 *  ----------TextView  标题
 *  -------TextView 未读消息小红点
 *
 */
class PTabItem : FrameLayout {

    /**
     *  小图标
     *  最好传入 StateListDrawable 对象
     *  来实现不同状态显示不同图标
     */
    private lateinit var mIcon: ImageView
    /**小图标*/
    private lateinit var mTvTitle: TextView

    private lateinit var mContext: Context

    private var mTabPosition = -1
    /**消息未读数*/
    private lateinit var mTvUnreadCount: TextView

    /**
     *  常用构造函数
     *  @param context 上下文
     *  @param icon 小图标
     *  @param title 标题
     */
    constructor(
        context: Context,
        icon: Int,
        title: CharSequence
    ) : this(context, null, icon, title)

    /**
     *  这个构造函数无需在外部直接调用
     *  直接调用第一个构造函数即可
     */
    constructor(
        context: Context,
        attrs: AttributeSet?,
        icon: Int,
        title: CharSequence
    ) : this(context, attrs, 0, icon, title)

    /**
     *  这个构造函数无需在外部直接调用
     *  直接调用第一个构造函数即可
     */
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        icon: Int,
        title: CharSequence
    ) : super(context, attrs, defStyleAttr) {
        init(context, icon, title)
    }

    /**
     *  初始化
     *  @param context 上下文
     *  @param icon 小图标
     *  @param title 标题
     */
    private fun init(context: Context, icon: Int, title: CharSequence) {
        mContext = context
        /**
         *  创建第二布局
         */
        val container = LinearLayout(context)
        container.orientation = LinearLayout.VERTICAL
        val containerParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        containerParams.gravity = Gravity.CENTER
        container.layoutParams = containerParams

        /**
         *  设置图标
         */
        mIcon = ImageView(context)
        /**
         * TypedValue.applyDimension是一个将各种单位的值转换为像素的方法
         * 比如这里就是把 23dp 转换成 px
         * 注：DisplayMetrics是一个获取屏幕信息的类，density是设备密度
         */
        val size = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            23F,
            resources.displayMetrics
        )).toInt()

        /**
         * 设置图标的宽度和高度
         */
        val imageParams = LinearLayout.LayoutParams(size, size)
        mIcon.setImageResource(icon)
        mIcon.layoutParams = imageParams

        container.addView(mIcon)

        /**
         * 设置标题
         */
        mTvTitle = TextView(context)
        mTvTitle.text = title
        val titleParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titleParams.topMargin = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            2F,
            resources.displayMetrics
        )).toInt()
        /**
         * 设置标题字体大小
         */
        mTvTitle.textSize = 11F
        /**
         * 设置标题原本显示的颜色
         * */
        mTvTitle.setTextColor(ContextCompat.getColor(context, R.color.C_000))
        mTvTitle.layoutParams = titleParams
        container.addView(mTvTitle)
        /**
         *  将第二布局加入第一布局
         */
        addView(container)

        /**
         *  设置消息小红点
         */
        val min = dip2px(context, 20F)
        val padding = dip2px(context, 5F)
        mTvUnreadCount = TextView(context)
        /**设置未读信息数目的背景*/
        mTvUnreadCount.setBackgroundResource(R.drawable.sp_msg_bubble)
        mTvUnreadCount.minWidth = min
        /**设置未读信息数目字体的颜色*/
        mTvUnreadCount.setTextColor(Color.WHITE)
        mTvUnreadCount.setPadding(padding, 0, padding, 0)
        mTvUnreadCount.gravity = Gravity.CENTER
        val tvUnReadParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, min)
        tvUnReadParams.gravity = Gravity.CENTER
        tvUnReadParams.leftMargin = dip2px(context, 17F)
        tvUnReadParams.bottomMargin = dip2px(context, 14F)
        mTvUnreadCount.layoutParams = tvUnReadParams
        mTvUnreadCount.visibility = View.GONE
        /**
         *  将消息小红点加入第一布局
         */
        addView(mTvUnreadCount)
    }

    fun setTabPosition(position: Int) {
        mTabPosition = position
        if (position == 0) {
            isSelected = true
        }
    }

    fun getTabPosition() = mTabPosition

    /**
     * 设置未读数量
     */
    fun setUnreadCount(num: Int) {
        if (num <= 0) {
            mTvUnreadCount.text = "0"
            mTvUnreadCount.visibility = View.GONE
        } else {
            mTvUnreadCount.visibility = View.VISIBLE
            if (num > 99) {
                mTvUnreadCount.text = "99+"
            } else {
                mTvUnreadCount.text = "$num"
            }
        }
    }

    /**
     * 获取当前未读数
     */
    fun getUnreadCount(): Int {
        var count = 0
        if (TextUtils.isEmpty(mTvUnreadCount.text)) {
            return count
        }
        if (mTvUnreadCount.text.toString() == "99+") {
            return 99
        }
        try {
            count = mTvUnreadCount.text.toString().toInt()
        } catch (ignored: Exception) {
        }
        return count
    }

    /**
     * TypedValue.applyDimension是一个将各种单位的值转换为像素的方法
     * 比如这里就是把 23dp 转换成 px
     * 注：DisplayMetrics是一个获取屏幕信息的类，density是设备密度
     */
    private fun dip2px(context: Context, dp: Float): Int {
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )).toInt()
    }

    /**
     *  FrameLayout 选中之后要做的事
     *  因为每一子项最外层是FrameLayout包裹
     */
    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if(selected){
            mTvTitle.setTextColor(Color.RED)
        }else{
            mTvTitle.setTextColor(Color.BLACK)
        }
    }
}