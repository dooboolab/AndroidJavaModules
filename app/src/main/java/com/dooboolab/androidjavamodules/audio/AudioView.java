package com.dooboolab.androidjavamodules.audio;

import com.dooboolab.androidjavamodules.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AudioView extends AppCompatActivity implements AudioPresenter.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.audio_view);
  }
}
