package site.wuao.core.ui.widget.status;

import android.content.Context;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

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
public class LoadingStatus extends AbstractStatus {
    public LoadingStatus(Context context) {
        super(context);
    }

    @Override
    public void onCreateView(View view) {
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        animationView.setSpeed(2f);
    }


    @Override
    public int getContentView() {
        return R.layout.status_loading;
    }
}
