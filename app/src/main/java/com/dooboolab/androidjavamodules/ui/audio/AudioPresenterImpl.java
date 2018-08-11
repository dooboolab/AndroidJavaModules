package com.dooboolab.androidjavamodules.ui.audio;

import android.view.View;

public class AudioPresenterImpl implements AudioPresenter{
  private AudioModel model;
  private View view;

  public AudioPresenterImpl(View view) {
    this.model = new AudioModel();
    this.view = view;
  }

  /*
   * Recording
   */
  public void startRecord(final String path) {

  }

  public void stopRecord() {

  }

  /*
   * Playing
   */
  public void startPlaying(final String path) {

  }

  public void stopPlaying() {

  }

  public void oausePlaying() {

  }

  public void resumePlaying() {

  }

  public void seekToPlaying(int sec) {

  }
}
