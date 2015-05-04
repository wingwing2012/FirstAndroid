package com.example.wingwing.first;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class GalleryActivity extends Activity {
    Gallery gallery;
    ImageView imageView;
    int[] imageIds = new int[]{
            R.drawable.autumn1, R.drawable.autumn2, R.drawable.autumn3, R.drawable.autumn4, R.drawable.autumn5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
        gallery = (Gallery) findViewById(R.id.gallery);

        imageView = (ImageView) findViewById(R.id.imageView);

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
                ImageView imageView = new ImageView(GalleryActivity.this);
                imageView.setImageResource(imageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(75, 100));
                TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
                imageView.setBackgroundResource(typedArray.getResourceId(R.styleable.Gallery_Item_background, 0));
                return imageView;
            }
        };
        gallery.setAdapter(adapter);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(imageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //hello world
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expandable_list, menu);
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

}
