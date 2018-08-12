package com.dooboolab.androidjavamodules.activities.main;

import android.content.Context;
import android.content.Intent;

public interface MainPresenter {
  void initView();
  void openAudioActivity(Context context, Intent intent);
}
