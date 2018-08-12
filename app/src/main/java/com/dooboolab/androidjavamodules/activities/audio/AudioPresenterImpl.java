package com.dooboolab.androidjavamodules.activities.audio;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AudioPresenterImpl implements AudioPresenter{
  final private String TAG = "AudioPresenterImpl";
  final private Handler recordHandler = new Handler();
  final private Handler playHandler = new Handler();
  final private AudioModel model;
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
    if (
        ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
        || ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(activity, new String[]{
          Manifest.permission.RECORD_AUDIO,
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
      }, 0);
      return;
    }

    Log.d(TAG, "startRecord");

    if (path == null) {
      path = AudioModel.DEFAULT_FILE_LOCATION;
    }
    this.view.showRecordPlay();

    if (this.model.getMediaRecorder() == null) {
      this.model.setMediaRecorder(new MediaRecorder());
      this.model.getMediaRecorder().setAudioSource(MediaRecorder.AudioSource.MIC);
      this.model.getMediaRecorder().setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
      this.model.getMediaRecorder().setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
      this.model.getMediaRecorder().setOutputFile(path);
    }

    try {
      this.model.getMediaRecorder().prepare();
      this.model.getMediaRecorder().start();

      final long systemTime = SystemClock.elapsedRealtime();
      this.model.setRecorderTicker(new Runnable() {
        @Override
        public void run() {
          long time = SystemClock.elapsedRealtime() - systemTime;
          Log.d(TAG, "elapsedTime: " + SystemClock.elapsedRealtime());
          Log.d(TAG, "time: " + time);

          DateFormat format = new SimpleDateFormat("mm:ss.SS", Locale.US);
          String displayTime = format.format(time);
          model.setRecordTime(time);
          view.setRecorderTimer(displayTime);

          recordHandler.postDelayed(model.getRecorderTicker(), model.RECORD_DELAY_MILLIS);
        }
      });
      this.model.getRecorderTicker().run();
    } catch (Exception e) {
      Log.e(TAG, "Exception: ", e);
    }
  }

  public void stopRecord() {
    view.showRecordStop();

    if (this.model.getMediaRecorder() == null) {
      Log.d(TAG, "mediaRecorder is null");
      return;
    }
    this.model.getMediaRecorder().stop();
    this.model.getMediaRecorder().release();
    this.model.setMediaRecorder(null);
    recordHandler.removeCallbacks(this.model.getRecorderTicker());
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
