package com.example.wingwing.first;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CallPrimeActivity extends ActionBarActivity {
    static final String UPPER_NUM = "upper";
    EditText mET = null;
    CalThread callT = null;
    class CalThread extends Thread {
        public Handler mHandler;
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (null != msg && 0x123 == msg.what) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer> nums = new ArrayList<Integer>();
                        for (int i = 2; i <= upper; i++) {
                            boolean isPrime = true;
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                if (i != 2 && i % j == 0){
                                    isPrime = false;
                                    break;
                                }
                            }
                            if (isPrime) {
                                nums.add(i);
                            }
                        }
                        Toast.makeText(CallPrimeActivity.this, nums.toString(), Toast.LENGTH_LONG).show();
                    }


                }
            };
            Looper.loop();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_prime);
        mET = (EditText)findViewById(R.id.editText);
        callT = new CalThread();
        callT.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_prime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cal(View view) {
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, Integer.parseInt(mET.getText().toString()));
        msg.setData(bundle);
        callT.mHandler.sendMessage(msg);
    }
}
