package com.dooboolab.androidjavamodules.ui.audio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.util.Timer;
import java.util.TimerTask;

public class AudioModel {
  final private static String DEFAULT_FILE_LOCATION = Environment.getExternalStorageDirectory().getPath();

  private MediaRecorder mediaRecorder;
  private MediaPlayer mediaPlayer;

  private TimerTask mTask;
  private Timer mTimer;

  public static String getDefaultFileLocation() {
    return DEFAULT_FILE_LOCATION;
  }

  public MediaRecorder getMediaRecorder() {
    if (this.mediaRecorder == null) {
      this.mediaRecorder = new MediaRecorder();
    }
    return mediaRecorder;
  }

  public void setMediaRecorder(MediaRecorder mediaRecorder) {
    this.mediaRecorder = mediaRecorder;
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
