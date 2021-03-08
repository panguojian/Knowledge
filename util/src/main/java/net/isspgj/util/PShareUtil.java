package net.isspgj.util;

import android.app.Activity;

import androidx.core.app.ShareCompat;

/**
 * Created by pgj on 2021/1/21
 **/

/**
 * 一个用原生api实现的分享功能工具
 * 为什么不适用静态方法：
 * 在静态方法里面持有activity引用会造成内存泄漏
 */
public class PShareUtil {
    /**
     * @param activity
     * @param shareText 要分享的内容
     */
    public void shareText(Activity activity,String shareText){
        shareText(activity,shareText,"选择要分享到的平台");
    }

    /**
     * @param activity
     * @param shareText 要分享的内容
     * @param chooserTitle 选择分享平台面板的标题
     */
    public void shareText(Activity activity,String shareText,String chooserTitle){
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(activity)
                .setText(shareText)
                .setChooserTitle(chooserTitle)
                .setType(mimeType)
                .startChooser();
    }
}
