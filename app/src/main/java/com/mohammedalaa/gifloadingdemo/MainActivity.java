package com.mohammedalaa.gifloadingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mohammedalaa.gifloading.LoadingView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    LoadingView loadingView;
    Timer timer;
    Button btnShow;
    private int time = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow=findViewById(R.id.btn_show);
        loadingView= (LoadingView) findViewById(R.id.loading_view);
    }



    public void show(View view) {
        loadingView.showLoading();
        btnShow.setEnabled(false);
        startTimer();

    }


    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        btnShow.setText(String.format(Locale.getDefault(), "%d", time));
                        if (time > 0)
                            time -= 1;
                        else {
                            btnShow.setText(R.string.show_loading);
                            btnShow.setEnabled(true);
                            loadingView.hideLoading();
                            time=15;
                            timer.cancel();
                            timer.purge();

                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }







    public void showToast(View view) {
        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
    }
}
