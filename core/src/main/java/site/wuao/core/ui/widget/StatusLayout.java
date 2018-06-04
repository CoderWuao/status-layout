package site.wuao.core.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    public static final int STATUS_EMPTY = 0;
    /** 网络错误 */
    public static final int STATUS_NETWORK_ERROR = 1;

    /** 状态列表 */
    public List<View> mStatusLayoutList = new ArrayList<>();

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public StatusLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 索引条的三参构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        /*
         * 以下顺序必须与常量定义的顺序一致
         */
        LayoutInflater inflater = LayoutInflater.from(context);
        // 其他状态(静态图片)
        mStatusLayoutList.add(inflater.inflate(R.layout.status_layout, this, false));
        // 默认隐藏
        setVisibility(View.GONE);
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
        } else {
            int index = 0;
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
}
