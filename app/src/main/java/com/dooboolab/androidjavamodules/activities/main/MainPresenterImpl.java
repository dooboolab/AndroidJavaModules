package com.dooboolab.androidjavamodules.activities.main;

import android.content.Context;
import android.content.Intent;

public class MainPresenterImpl implements MainPresenter {
  private MainModel model;
  private MainView view;

  MainPresenterImpl(MainView view) {
    this.view = view;
  }

  @Override
  public void initView() {
    view.initView();
  }

  @Override
  public void openAudioActivity(Context context, Intent intent) {
    context.startActivity(intent);
  }
}
