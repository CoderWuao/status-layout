package site.wuao.core.ui.widget.status;

import android.content.Context;
import android.view.View;

import site.wuao.core.R;

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
public class EmptyStatus extends AbstractStatus {
    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public EmptyStatus(Context context) {
        super(context);
    }

    @Override
    public void onCreateView(View view) {

    }

    @Override
    public int getContentView() {
        return R.layout.status_empty;
    }
}
