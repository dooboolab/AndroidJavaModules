package com.dooboolab.androidjavamodules;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dooboolab.androidjavamodules.ui.main.MainActivity;

public class LoadingActivity extends AppCompatActivity {
  private final String TAG = "LoadingActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loading);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent i = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(i);
        finish();
      }
    }, 1500);
  }
}
