package site.wuao.core.ui.widget.status;

import android.content.Context;
import android.view.View;

/**
 * 页面
 *
 * @author wuao
 * @date 2018.06.04
 * @note -
 * ---------------------------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public abstract class AbstractStatus {
    /** 上下文 */
    private Context mContext;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public AbstractStatus(Context context) {
        mContext = context;
    }

    /**
     * 当创建了视图
     *
     * @param view 视图
     */
    public abstract void onCreateView(View view);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取内部布局
     *
     * @return 内容布局
     */
    public abstract int getContentView();
}
