package site.wuao.core.ui.widget.status;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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
public class DefaultEmptyStatus extends AbstractStatus {
    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public DefaultEmptyStatus(Context context) {
        super(context);
    }

    @Override
    public void onCreateView(View view) {
//        ImageView ivIcon = view.findViewById(R.id.iv_status_layout_icon);
        TextView tvDes = view.findViewById(R.id.tv_status_layout_des);
//        ivIcon.setImageResource(R.drawable.ic_status_empty);
        tvDes.setText("抱歉，数据为空，请点击屏幕重试！");
    }

    @Override
    public int getContentView() {
        return R.layout.status_layout;
    }
}
