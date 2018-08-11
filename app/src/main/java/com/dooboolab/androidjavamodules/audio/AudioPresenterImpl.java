package com.dooboolab.androidjavamodules.audio;

import android.view.View;

public class AudioPresenterImpl implements AudioPresenter{
  private AudioModel audioModel;
  private View audioView;

  public AudioPresenterImpl(View view) {
    this.audioModel = new AudioModel();
    this.audioView = view;
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
