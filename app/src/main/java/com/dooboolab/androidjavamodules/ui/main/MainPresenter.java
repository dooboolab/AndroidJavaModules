package com.dooboolab.androidjavamodules.ui.main;

import android.content.Context;
import android.content.Intent;

public interface MainPresenter {
  void initView();
  void openAudioActivity(Context context, Intent intent);
}
