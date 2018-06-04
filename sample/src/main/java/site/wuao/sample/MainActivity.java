package site.wuao.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import site.wuao.core.ui.widget.StatusLayout;
import site.wuao.core.utils.ViewUtils;

public class MainActivity extends AppCompatActivity {

    private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStatusLayout = new StatusLayout(this);
        ViewUtils.coverView(this, findViewById(R.id.fl), mStatusLayout, true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_A) {
            mStatusLayout.showStatus(StatusLayout.STATUS_EMPTY, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (keyCode == KeyEvent.KEYCODE_B) {
            mStatusLayout.showStatus(StatusLayout.STATUS_NETWORK_ERROR, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }
}
