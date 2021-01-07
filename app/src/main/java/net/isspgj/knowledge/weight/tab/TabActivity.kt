package net.isspgj.knowledge.weight.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.isspgj.knowledge.R
import net.isspgj.weight.tab.PTabBar
import net.isspgj.weight.tab.PTabItem

/**
 * 此活动用来演示PTabBar和PTabItem的简单用法
 */
class TabActivity : AppCompatActivity() {
    private lateinit var tabPTabBar: PTabBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        initTab()
    }

    private fun initTab() {
        tabPTabBar = findViewById(R.id.tb_demo)
        /**
         * 创建 PTabItem
         */
        val tabItem1 = PTabItem(this, R.drawable.selector_tab_deals, "推荐")
        /**
         * 设置消息未读数
         */
        tabItem1.setUnreadCount(5)
        tabPTabBar.addItem(tabItem1)
        tabPTabBar.addItem(PTabItem(this, R.drawable.selector_tab_sku, "商品"))
        tabPTabBar.addItem(PTabItem(this, R.drawable.selector_tab_moonshow, "晒图"))
        tabPTabBar.addItem(PTabItem(this, R.drawable.selector_tab_notification, "通知"))
        tabPTabBar.addItem(PTabItem(this, R.drawable.selector_tab_account, "我的"))

        /**
         *  设置子项点击事件回调
         */
        tabPTabBar.setOnTabSelectedListener(object : PTabBar.OnTabSelectedListener {
            // 重新选中
            // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
            override fun onTabReselected(position: Int) {}

            // 选中
            override fun onTabSelected(position: Int, prePosition: Int) {

            }

            // 未选中
            override fun onTabUnselected(position: Int) {}
        })
    }
}