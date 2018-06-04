package site.wuao.core.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.TimerTask;

/**
 * <p>视图相关的Utils</p><br>
 *
 * @author lwc
 * @date 2017/3/14 18:18
 * @note -
 * findViewByXY :在View上的x,y值获得子View
 * getTouchTarget :通过xy在View上获得点击的目标view
 * isTouchPointInView :判断xy是否在view的大小内
 * setTextDrawable :给TextView设置Drawable,如果不设置，传0
 * -------------------------------------------------------------------------------------------------
 * @modified - lwc
 * @date - 2017/4/13 14.39
 * @note -
 * setImageViewSelector 通过图片的tint值设置图片的selector效果，Clickable=true才有效果
 * setViewSelector 通过代码写出shape,再通过代码设置selector，Clickable=true才有效果
 */
public class ViewUtils {
    /**
     * 构造类
     */
    private ViewUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 使用coverView遮盖view
     *
     * @param context 上下文
     * @param view 被遮盖的view
     * @param coverView 遮盖的view
     * @param isReplace 是否替换被遮盖的view(保持view的相对层级关系)
     * @return 替换后的View
     */
    public static void coverView(final Context context, final View view, final View coverView, final boolean isReplace) {
        if (null == view || null == coverView) {

            return;
        }
        view.post(new TimerTask() {
            @Override
            public void run() {
                if (view.getParent() instanceof ViewGroup) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (isReplace) {
                        int index = parent.indexOfChild(view);
                        parent.removeView(view);
                        FrameLayout child = new FrameLayout(context);
                        child.setLayoutParams(layoutParams);
                        child.addView(view);
                        child.addView(coverView);
                        parent.addView(child, index);
                    } else {
                        coverView.setLayoutParams(layoutParams);
                        ((ViewGroup) view.getParent()).addView(coverView);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            coverView.setElevation(1000);
                        }
                    }
                }
            }
        });
    }
}
