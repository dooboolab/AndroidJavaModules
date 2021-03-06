package com.dooboolab.androidjavamodules.activities.audio;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPresenterImpl implements AudioPresenter{
  final private String TAG = "AudioPresenterImpl";
  final private Handler recordHandler = new Handler();
  final private Handler playHandler = new Handler();
  final private AudioModel model;
  private AudioView view;
  private Timer mTimer = new Timer();

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

          DateFormat format = new SimpleDateFormat("mm:ss:SS", Locale.US);
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
  public void startPlaying(final Activity activity, final String path) {
    if (this.model.getMediaPlayer() != null) {
      Boolean isPaused = !this.model.getMediaPlayer().isPlaying()
          && this.model.getMediaPlayer().getCurrentPosition() > 1;

      if (isPaused) {
        this.model.getMediaPlayer().start();
        return;
      }

      Log.e(TAG, "Player is already running. Stop it first.");
      return;
    } else {
      this.model.setMediaPlayer(new MediaPlayer());
    }

    final AudioView audioView = this.view;
    mTimer = new Timer();

    try {
      if (path == null) {
        this.model.getMediaPlayer().setDataSource(AudioModel.DEFAULT_FILE_LOCATION);
      } else {
        this.model.getMediaPlayer().setDataSource(path);
      }

      this.model.getMediaPlayer().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(final MediaPlayer mp) {
          Log.d(TAG, "mediaPlayer prepared and start");
          mp.start();

          /*
           * Set timer task to send event to RN.
           */
          TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
//              WritableMap obj = Arguments.createMap();
//              obj.putInt("duration", mp.getDuration());
//              obj.putInt("current_position", mp.getCurrentPosition());
//              sendEvent(reactContext, "rn-playback", obj);

              long time = mp.getCurrentPosition();
              DateFormat format = new SimpleDateFormat("mm:ss:SS", Locale.US);
              final String displayTime = format.format(time);
              model.setPlayTime(time);

              activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  audioView.setPlayerTimer(displayTime);
                }
              });
            }
          };

          mTimer.schedule(mTask, 0, model.PLAY_DELAY_MILLIS);
          String resolvedPath = path == null ? AudioModel.DEFAULT_FILE_LOCATION : path;
        }
      });
      /*
       * Detect when finish playing.
       */
      this.model.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
          /*
           * Send last event
           */
//          WritableMap obj = Arguments.createMap();
//          obj.putInt("duration", mp.getDuration());
//          obj.putInt("current_position", mp.getDuration());
//          obj.putInt("justFinished", 1);
//          sendEvent(reactContext, "rn-playback", obj);

          /*
           * Reset player.
           */
          Log.d(TAG, "Plays completed.");
          mTimer.cancel();
          mp.stop();
          mp.release();
          model.setMediaPlayer(null);
        }
      });
      this.model.getMediaPlayer().prepare();
    } catch (Exception e) {
      Log.e(TAG, "startPlay() exception");
    }
  }

  public void stopPlaying() {
     mTimer.cancel();

    if (this.model.getMediaPlayer() == null) {
      return;
    }

    try {
      this.model.getMediaPlayer().stop();
      this.model.getMediaPlayer().release();
      this.model.setMediaPlayer(null);
    } catch (Exception e) {
      Log.e(TAG, "stopPlay exception: " + e.getMessage());
    }
  }

  public void pausePlaying() {
    if (this.model.getMediaPlayer() == null) {
      return;
    }

    try {
      this.model.getMediaPlayer().pause();
    } catch (Exception e) {
      Log.e(TAG, "pausePlay exception: " + e.getMessage());
    }
  }

  public void resumePlaying() {
    if (this.model.getMediaPlayer() == null) {
      return;
    }

    if (this.model.getMediaPlayer().isPlaying()) {
      return;
    }

    try {
      this.model.getMediaPlayer().seekTo(this.model.getMediaPlayer().getCurrentPosition());
      this.model.getMediaPlayer().start();
    } catch (Exception e) {
      Log.e(TAG, "mediaPlayer resume: " + e.getMessage());
    }
  }

  public void seekToPlaying(int sec) {
    if (this.model.getMediaPlayer() == null) {
      return;
    }

    int currentMillis = this.model.getMediaPlayer().getCurrentPosition();
    int millis = sec * 1000 + currentMillis;

    Log.d(TAG, "seekTo: " + millis);

    this.model.getMediaPlayer().seekTo(millis);
  }
}
