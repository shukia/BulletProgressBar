package com.shlmlkzdh.bulletprogressbar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shlmlkzdh.bulletprogressbar.BulletProgressBar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BulletProgressBar progressBar0 = (BulletProgressBar) findViewById(R.id.bpb0);
        progressBar0.setProgress(0);

        BulletProgressBar progressBar1 = (BulletProgressBar) findViewById(R.id.bpb1);
        progressBar1.setProgress(1);

        BulletProgressBar progressBar2 = (BulletProgressBar) findViewById(R.id.bpb2);
        progressBar2.setProgress(2);

        BulletProgressBar progressBar3 = (BulletProgressBar) findViewById(R.id.bpb3);
        progressBar3.setProgress(3);

        BulletProgressBar progressBar5 = (BulletProgressBar) findViewById(R.id.bpb5);
        progressBar5.setProgress(5);

    }

}
