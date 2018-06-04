package site.wuao.core.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.security.InvalidParameterException;
import java.util.TimerTask;

import site.wuao.core.ui.widget.status.AbstractStatus;

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

//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        // 正在加载(动画)
//        View inflate = inflater.inflate(R.layout.status_loading, this, false);
//        LottieAnimationView animation = inflate.findViewById(R.id.animation_view);
//        animation.setSpeed(2f);
//        mStatusLayoutList.add(inflate);
//        // 其他状态(静态图片)
//        mStatusLayoutList.add(inflater.inflate(R.layout.status_layout, this, false));

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

    public void showStatus(View view) {
//        if (status < 0) {
//            setVisibility(View.GONE);
//            return;
//        }

//        int index = status == STATUS_LOADING ? 0 : 1;
//        View view = mStatusLayoutList.get(index);

        // 清除当前view
        if (getChildAt(0) != null) {
            removeViewAt(0);
        }
        // 添加状态view
        addView(view);
        // 显示
        setVisibility(VISIBLE);
    }

    public void showStatus(AbstractStatus loadingStatus) {
        showStatus(loadingStatus, null);
    }

    public void showStatus(AbstractStatus loadingStatus, OnClickListener onRootViewClickListener) {
        LayoutInflater inflater = LayoutInflater.from(loadingStatus.getContext());
        View inflate = inflater.inflate(loadingStatus.getContentView(), this, false);
        if (onRootViewClickListener != null) {
            inflate.setOnClickListener(onRootViewClickListener);
        }
        loadingStatus.onCreateView(inflate);
        showStatus(inflate);
    }
}
