package site.wuao.core.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import site.wuao.core.R;

/**
 * 状态布局
 *
 * @author wuao
 * @date 2018.06.03
 * @note -
 * -------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public class StatusLayout extends RelativeLayout {
    /** 隐藏 */
    public static final int STATUS_HIDE = -1;
    /** 暂无数据 */
    public static final int STATUS_EMPTY = 1;
    /** 网络错误 */
    public static final int STATUS_NETWORK_ERROR = 2;
    /** 正在加载 */
    public static final int STATUS_LOADING = 3;

    /** 状态列表 */
    public List<View> mStatusLayoutList = new ArrayList<>();

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public StatusLayout(final Context context, final View view) {
        super(context);

        if (context == null) {
            throw new InvalidParameterException("context can not be null.");
        }
        if (view == null) {
            throw new InvalidParameterException("view can not be null.");
        }

        /*
         * 以下顺序必须与常量定义的顺序一致
         */
        LayoutInflater inflater = LayoutInflater.from(context);
        // 正在加载(动画)
        View inflate = inflater.inflate(R.layout.status_loading, this, false);
        LottieAnimationView animation = inflate.findViewById(R.id.animation_view);
        animation.setSpeed(2f);
        mStatusLayoutList.add(inflate);
        // 其他状态(静态图片)
        mStatusLayoutList.add(inflater.inflate(R.layout.status_layout, this, false));
        // 默认隐藏
        setVisibility(View.GONE);

        view.post(new TimerTask() {
            @Override
            public void run() {
                if (view.getParent() instanceof ViewGroup) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    // 原参数和位置
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int index = parent.indexOfChild(view);
                    // 移除
                    parent.removeView(view);
                    FrameLayout child = new FrameLayout(context);
                    child.setLayoutParams(layoutParams);
                    // 原本的在底层
                    child.addView(view);
                    // 上层
                    child.addView(StatusLayout.this);
                    parent.addView(child, index);
                }
            }
        });
    }

    /**
     * 显示状态
     *
     * @param status 状态码(参见STATUS_LOADING等)
     */
    public void showStatus(int status) {
        showStatus(status, null);
    }

    /**
     * 显示状态
     *
     * @param status 状态码(参见STATUS_LOADING等)
     * @param retryClickListener 重试点击监听(LOADING状态无效)
     */
    public void showStatus(int status, View.OnClickListener retryClickListener) {
        if (status < 0) {
            setVisibility(View.GONE);
            return;
        }

        int index = status == STATUS_LOADING ? 0 : 1;
        try {
            View view = mStatusLayoutList.get(index);
            // 清除当前view
            if (getChildAt(0) != null) {
                removeViewAt(0);
            }
            // 添加状态view
            addView(view);
            // 显示
            setVisibility(VISIBLE);

            if (status == STATUS_EMPTY) {
                ((ImageView) view.findViewById(R.id.iv_status_layout_icon)).setImageResource(R.drawable.ic_empty);
                ((TextView) view.findViewById(R.id.tv_status_layout_des)).setText("抱歉，暂无数据，请点击屏幕重试！");
            } else if (status == STATUS_NETWORK_ERROR) {
                ((ImageView) view.findViewById(R.id.iv_status_layout_icon)).setImageResource(R.drawable.ic_network_error02);
                ((TextView) view.findViewById(R.id.tv_status_layout_des)).setText("抱歉，网络异常，请点击屏幕重试！");
            }

            // 设置点击监听
            LinearLayout rootView = view.findViewById(R.id.ll_status_layout_root);
            if (rootView != null) {
                if (retryClickListener != null) {
                    rootView.setOnClickListener(retryClickListener);
                    rootView.setVisibility(View.VISIBLE);
                } else {
                    rootView.setVisibility(View.INVISIBLE);
                }
            }

        } catch (Exception e) {
            // 不处理异常
        }
    }
}
