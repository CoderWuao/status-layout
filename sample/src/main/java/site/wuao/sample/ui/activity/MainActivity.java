package site.wuao.sample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import site.wuao.sample.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                break;
            case KeyEvent.KEYCODE_B:
                break;
            case KeyEvent.KEYCODE_C:
                break;
            default:
                break;
        }
        return true;
    }
}
