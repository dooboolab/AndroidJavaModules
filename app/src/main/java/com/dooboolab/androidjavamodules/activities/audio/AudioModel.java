package com.dooboolab.androidjavamodules.activities.audio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class AudioModel {
  final public static String DEFAULT_FILE_LOCATION = Environment.getExternalStorageDirectory().getPath() + "/default.mp4";
  final public int RECORD_DELAY_MILLIS = 10;

  private MediaRecorder mediaRecorder;
  private Runnable recorderTicker;
  private long recordTime = 0;

  private MediaPlayer mediaPlayer;
  private TimerTask mTask;
  private Timer mTimer;

  public MediaRecorder getMediaRecorder() {
    return mediaRecorder;
  }

  public void setMediaRecorder(MediaRecorder mediaRecorder) {
    this.mediaRecorder = mediaRecorder;
  }

  public Runnable getRecorderTicker() {
    return recorderTicker;
  }

  public void setRecorderTicker(Runnable recorderTicker) {
    this.recorderTicker = recorderTicker;
  }

  public long getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(long recordTime) {
    this.recordTime = recordTime;
  }

  public MediaPlayer getMediaPlayer() {
    if (this.mediaPlayer == null) {
      this.mediaPlayer = new MediaPlayer();
    }
    return mediaPlayer;
  }

  public void setMediaPlayer(MediaPlayer mediaPlayer) {
    this.mediaPlayer = mediaPlayer;
  }

  public TimerTask getmTask() {
    return mTask;
  }

  public void setmTask(TimerTask mTask) {
    this.mTask = mTask;
  }

  public Timer getmTimer() {
    return mTimer;
  }

  public void setmTimer(Timer mTimer) {
    this.mTimer = mTimer;
  }
}
