package com.dooboolab.androidjavamodules.ui.main;

import android.content.Context;
import android.content.Intent;
import com.dooboolab.androidjavamodules.ui.audio.AudioActivity;

public class MainPresenterImpl implements MainPresenter {
  private MainModel model;
  private MainView view;

  public MainPresenterImpl(MainView view) {
    this.view = view;
  }

  @Override
  public void initView() {
    view.initView();
  }

  @Override
  public void openAudioActivity(Context context) {
    Intent i = new Intent(context, AudioActivity.class);
    context.startActivity(i);
  }
}
