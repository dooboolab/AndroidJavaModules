package com.dooboolab.androidjavamodules.ui.audio;

import com.dooboolab.androidjavamodules.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AudioActivity extends AppCompatActivity implements AudioView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio);
  }
}
