package ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhonglian.battleworld.R;

import ui.widget.UnlockView;

public class UnlockActivity extends Activity {
    private String pwd;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        final UnlockView unlock = (UnlockView) findViewById(R.id.unlock);
        show = (TextView) findViewById(R.id.show);
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button6 = (Button) findViewById(R.id.button6);
        final Button button7 = (Button) findViewById(R.id.button7);
        final Button button8 = (Button) findViewById(R.id.button8);
        final Button button9 = (Button) findViewById(R.id.button9);
        unlock.setMode(UnlockView.CREATE_MODE);
        unlock.setGestureListener(new UnlockView.CreateGestureListener() {
            @Override
            public void onGestureCreated(String result) {
                pwd = result;
                Toast.makeText(UnlockActivity.this, "Set Gesture Succeeded!", Toast.LENGTH_SHORT).show();
                unlock.setMode(UnlockView.CHECK_MODE);
                char [] stringArr = pwd.toCharArray();
                for (int i = 0; i < stringArr.length; i++) {
                    char c = stringArr[i];
                    String s = String.valueOf(c);
                    switch (Integer.parseInt(s)){
                        case 1:
                            button1.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 2:
                            button2.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 3:
                            button3.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 4:
                            button4.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 5:
                            button5.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 6:
                            button6.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 7:
                            button7.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 8:
                            button8.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                        case 9:
                            button9.setBackground(getDrawable(R.drawable.circle_shape_select));
                            break;
                    }
                }
                show.setText("再次绘制解锁图案");
            }
        });
        unlock.setOnUnlockListener(new UnlockView.OnUnlockListener() {
            @Override
            public boolean isUnlockSuccess(String result) {
                if (result.equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onSuccess() {
                Toast.makeText(UnlockActivity.this, "Check Succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(UnlockActivity.this, "Check Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
