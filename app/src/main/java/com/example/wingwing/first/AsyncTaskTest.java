package com.example.wingwing.first;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class AsyncTaskTest extends ActionBarActivity {

    private TextView mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        mShow = (TextView) findViewById(R.id.asycText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async_task_test, menu);
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

    public void download(View view) throws MalformedURLException {
        DownTask task = new DownTask(this);
        task.execute(new URL("https://www.crazyit.org/ethos.php"));
    }

    class DownTask extends AsyncTask<URL, Integer, String> {

        ProgressDialog pDialog;
        int hasRead = 0;
        Context mContext = null;

        public DownTask(Context ctx) {
            mContext = ctx;
        }

        @Override
        protected String doInBackground(URL... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URLConnection conn = params[0].openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            mShow.setText(result);
            pDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(mContext);
            pDialog.setTitle("任务正在执行中");
            pDialog.setMessage("任务正在执行中，敬请等待...");
            pDialog.setCancelable(false);
            pDialog.setMax(202);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mShow.setText("已经读取了 【" + values[0] + "】行！");
            pDialog.setProgress(values[0]);
        }
    }
}
