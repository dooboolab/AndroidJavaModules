package com.dooboolab.androidjavamodules.ui.audio;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

public class AudioPresenterImpl implements AudioPresenter{
  private final String TAG = "AudioPresenterImpl";
  private AudioModel model;
  private AudioView view;

  AudioPresenterImpl(AudioView view) {
    this.model = new AudioModel();
    this.view = view;
  }

  @Override
  public void initView() {
    view.initView();
  }

  @Override
  public void finishView() {
    view.finishView();
  }

  /*
   * Recording
   */
  public void startRecord(Activity activity, String path) {
    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
      return;
    }

    if (path == null) {
      path = AudioModel.getDefaultFileLocation();
    }
    this.view.showRecordPlay();

    this.model.getMediaRecorder().setAudioSource(MediaRecorder.AudioSource.MIC);
    this.model.getMediaRecorder().setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    this.model.getMediaRecorder().setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

    this.model.getMediaRecorder().setOutputFile(path);

    try {
      this.model.getMediaRecorder().prepare();
      this.model.getMediaRecorder().start();
      this.model.getMediaRecorder().setOnInfoListener(new MediaRecorder.OnInfoListener() {
        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {
          switch (what) {
            case MediaRecorder.MEDIA_RECORDER_INFO_UNKNOWN:
              Log.d(TAG, "MEDIA_RECORDER_INFO_UNKNOWN: " + extra);
              break;
            case MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED:
              Log.d(TAG, "MEDIA_RECORDER_INFO_MAX_DURATION_REACHED: " + extra);
              break;
            case MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED:
              Log.d(TAG, "MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED: " + extra);
              break;
          }
        }
      });
    } catch (Exception e) {
      Log.e(TAG, "Exception: ", e);
    }
  }

  public void stopRecord() {
    view.showRecordStop();

    this.model.getMediaRecorder().stop();
    this.model.getMediaRecorder().release();
    this.model.setMediaRecorder(null);
  }

  /*
   * Playing
   */
  public void startPlaying(String path) {
  }

  public void stopPlaying() {
  }

  public void pausePlaying() {

  }

  public void resumePlaying() {

  }

  public void seekToPlaying(int sec) {

  }
}
