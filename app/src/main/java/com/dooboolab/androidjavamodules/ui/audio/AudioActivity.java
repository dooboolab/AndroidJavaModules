package com.dooboolab.androidjavamodules.ui.audio;

import com.dooboolab.androidjavamodules.R;
import com.dooboolab.androidjavamodules.ui.main.MainPresenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AudioActivity extends AppCompatActivity implements AudioView {
  private AudioPresenter presenter;
  private TextView txtRecordTimer;
  private ImageButton btnRecord;
  private ImageButton btnRecordStop;

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
    btnRecord = findViewById(R.id.btn_record);
    btnRecordStop = findViewById(R.id.btn_record_stop);
    btnRecord.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.startRecord(activity, null);
      }
    });
    btnRecordStop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.stopRecord();
      }
    });
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
    switch (requestCode){
      case REQUEST_RECORD_AUDIO_PERMISSION:
        permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        break;
    }
    if (!permissionToRecordAccepted ) {
      // finish();
    }
  }
}
