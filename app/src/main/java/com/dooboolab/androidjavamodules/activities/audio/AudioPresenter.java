package com.dooboolab.androidjavamodules.activities.audio;

import android.app.Activity;

public interface AudioPresenter {
  void initView();
  void finishView();

  void startRecord(Activity activity, String path);
  void stopRecord();
  void startPlaying(String path);
  void stopPlaying();
  void pausePlaying();
  void resumePlaying();
  void seekToPlaying(int sec);
}
