package com.dooboolab.androidjavamodules.ui.audio;

public interface AudioPresenter {
  void startRecord(final String path);
  void stopRecord();
  void startPlaying(final String path);
  void stopPlaying();
  void oausePlaying();
  void resumePlaying();
  void seekToPlaying(int sec);
}
