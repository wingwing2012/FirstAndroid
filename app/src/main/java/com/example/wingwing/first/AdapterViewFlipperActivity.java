package com.example.wingwing.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class AdapterViewFlipperActivity extends Activity {

    int[] imageIds = new int[]{
            R.drawable.autumn1, R.drawable.autumn2, R.drawable.autumn3, R.drawable.autumn4, R.drawable.autumn5
    };

    AdapterViewFlipper mFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_flipper);
        mFlipper = (AdapterViewFlipper) findViewById(R.id.flipper);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(AdapterViewFlipperActivity.this);
                imageView.setImageResource(imageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        mFlipper.setAdapter(adapter);
    }

    public void prev(View view) {
        mFlipper.showPrevious();
        mFlipper.stopFlipping();
    }

    public void next(View view) {
        mFlipper.showNext();
        mFlipper.stopFlipping();
    }

    public void auto(View view) {
        mFlipper.startFlipping();
    }
}
