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
  final public int PLAY_DELAY_MILLIS = 10;

  private MediaRecorder mediaRecorder;
  private Runnable recorderTicker;
  private long recordTime = 0;

  private MediaPlayer mediaPlayer;
  private long playTime = 0;

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
    return mediaPlayer;
  }

  public void setMediaPlayer(MediaPlayer mediaPlayer) {
    this.mediaPlayer = mediaPlayer;
  }

  public long getPlayTime() {
    return playTime;
  }

  public void setPlayTime(long playTime) {
    this.playTime = playTime;
  }
}
