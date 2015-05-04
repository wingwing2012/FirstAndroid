package com.example.wingwing.first;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ConfigurationTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_test);
        Button bt = (Button)findViewById(R.id.changeOT);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = getResources().getConfiguration();
                if (Configuration.ORIENTATION_LANDSCAPE == config.orientation) {
                    ConfigurationTestActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                if (Configuration.ORIENTATION_PORTRAIT == config.orientation) {
                    ConfigurationTestActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE? "横向屏幕" : "竖向屏幕";
        Toast.makeText(this, "系统的屏幕方向发生改变" + "\n 修改后的屏幕方向为：" + screen, Toast.LENGTH_LONG).show();
    }

}
