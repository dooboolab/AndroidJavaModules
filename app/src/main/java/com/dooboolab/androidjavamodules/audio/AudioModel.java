package com.dooboolab.androidjavamodules.audio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.util.Timer;
import java.util.TimerTask;

public class AudioModel {
  final private static String FILE_LOCATION = "/sdcard/sound.mp4";

  private MediaRecorder mediaRecorder;
  private MediaPlayer mediaPlayer;

  private TimerTask mTask;
  private Timer mTimer;

  public MediaRecorder getMediaRecorder() {
    return mediaRecorder;
  }

  public void setMediaRecorder(MediaRecorder mediaRecorder) {
    this.mediaRecorder = mediaRecorder;
  }

  public MediaPlayer getMediaPlayer() {
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
