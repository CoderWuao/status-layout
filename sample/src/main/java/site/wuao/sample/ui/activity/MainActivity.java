package site.wuao.sample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import site.wuao.core.ui.widget.StatusLayout;
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
            case KeyEvent.KEYCODE_L:
                mStatusLayout.showStatus(StatusLayout.STATUS_LOADING, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case KeyEvent.KEYCODE_E:
                mStatusLayout.showStatus(StatusLayout.STATUS_EMPTY, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case KeyEvent.KEYCODE_N:
                mStatusLayout.showStatus(StatusLayout.STATUS_NETWORK_ERROR, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case KeyEvent.KEYCODE_H:
                mStatusLayout.showStatus(StatusLayout.STATUS_HIDE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }
}
