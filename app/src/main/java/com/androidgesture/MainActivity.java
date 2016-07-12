package com.androidgesture;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    float scale = 1f;
    ScaleGestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        detector = new ScaleGestureDetector(this,new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent event) {
    //  re-route the Touch Events to the ScaleListener class
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {


        float onScaleBegin = 0;
        float onScaleEnd = 0;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            Toast.makeText(getApplicationContext(),"Scale Begin" ,Toast.LENGTH_SHORT).show();
            onScaleBegin = scale;

            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

            Toast.makeText(getApplicationContext(),"Scale Ended", Toast.LENGTH_SHORT).show();
            onScaleEnd = scale;

            if (onScaleEnd > onScaleBegin){
                Toast.makeText(getApplicationContext(),"Scaled Up by a factor of  " + String.valueOf( onScaleEnd / onScaleBegin ), Toast.LENGTH_SHORT  ).show();
            }

            if (onScaleEnd < onScaleBegin){
                Toast.makeText(getApplicationContext(),"Scaled Down by a factor of  " + String.valueOf( onScaleBegin / onScaleEnd ), Toast.LENGTH_SHORT  ).show();
            }

            super.onScaleEnd(detector);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
