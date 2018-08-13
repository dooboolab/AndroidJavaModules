package com.dooboolab.androidjavamodules.activities.audio;

import com.dooboolab.androidjavamodules.R;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AudioActivity extends AppCompatActivity implements AudioView, View.OnClickListener{
  final private String TAG = "AudioActivity";

  private AudioPresenter presenter;
  private TextView txtRecordTimer;
  private TextView txtPlayTimer;
  private ImageButton btnRecord;
  private ImageButton btnRecordStop;
  private ImageButton btnPlay;
  private ImageButton btnPause;
  private ImageButton btnPlayStop;
  private ImageButton btnStepBack;
  private ImageButton btnStepForward;

  // Requesting permission to RECORD_AUDIO
  private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
  private boolean permissionToRecordAccepted = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio);
    setTitle("Audio");

    presenter = new AudioPresenterImpl(this);
    presenter.initView();
  }

  @Override
  public void initView() {
    final Activity activity = this;
    txtRecordTimer = findViewById(R.id.txt_record);
    txtPlayTimer = findViewById(R.id.txt_play);
    txtRecordTimer.setText("00:00:00");
    txtPlayTimer.setText(("00:00:00"));

    btnRecord = findViewById(R.id.btn_record);
    btnRecord.setOnClickListener(this);
    btnRecordStop = findViewById(R.id.btn_record_stop);
    btnRecordStop.setOnClickListener(this);
    btnPlay = findViewById(R.id.btn_play);
    btnPlay.setOnClickListener(this);
    btnPause = findViewById(R.id.btn_pause);
    btnPause.setOnClickListener(this);
    btnPlayStop = findViewById(R.id.btn_play_stop);
    btnPlayStop.setOnClickListener(this);
    btnStepBack = findViewById(R.id.btn_step_back);
    btnStepBack.setOnClickListener(this);
    btnStepForward = findViewById(R.id.btn_step_forward);
    btnStepForward.setOnClickListener(this);
  }

  @Override
  public void finishView() {
    finish();
  }

  @Override
  public void showRecordPlay() {
    btnRecordStop.setVisibility(View.VISIBLE);
    btnRecord.setVisibility(View.GONE);
  }

  @Override
  public void showRecordStop() {
    btnRecordStop.setVisibility(View.GONE);
    btnRecord.setVisibility(View.VISIBLE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQUEST_RECORD_AUDIO_PERMISSION:
        permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        break;
    }
    if (!permissionToRecordAccepted ) {
      // finish();
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_record:
        presenter.startRecord(this, null);
        break;
      case R.id.btn_record_stop:
        presenter.stopRecord();
        break;
      case R.id.btn_play:
        presenter.startPlaying(this, null);
        break;
      case R.id.btn_pause:
        presenter.pausePlaying();
        break;
      case R.id.btn_play_stop:
        presenter.stopPlaying();
        break;
      case R.id.btn_step_back:
        presenter.seekToPlaying(-3);
        break;
      case R.id.btn_step_forward:
        presenter.seekToPlaying(3);
        break;
    }
  }

  @Override
  public void setRecorderTimer(String txt) {
    Log.d(TAG, "setRecorderTimer: " + txt);
    this.txtRecordTimer.setText(txt);
  }

  @Override
  public void setPlayerTimer(String txt) {
    Log.d(TAG, "setPlayerTimer: " + txt);
    this.txtPlayTimer.setText(txt);
  }
}
