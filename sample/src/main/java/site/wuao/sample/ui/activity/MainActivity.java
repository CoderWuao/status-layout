package site.wuao.sample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import site.wuao.core.ui.widget.StatusLayout;
import site.wuao.core.ui.widget.status.DefaultEmptyStatus;
import site.wuao.core.ui.widget.status.DefaultLoadingStatus;
import site.wuao.sample.R;

public class MainActivity extends AppCompatActivity {
    private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStatusLayout = new StatusLayout(this, findViewById(R.id.fl));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                mStatusLayout.showStatus(new DefaultLoadingStatus(this));
                break;
            case KeyEvent.KEYCODE_B:
                mStatusLayout.showStatus(new DefaultEmptyStatus(this), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case KeyEvent.KEYCODE_C:
                mStatusLayout.showHide();
                break;
            default:
                break;
        }
        return true;
    }
}
