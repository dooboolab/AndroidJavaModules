package com.dooboolab.androidjavamodules.activities.audio;

public interface AudioView {
  void initView();
  void finishView();
  void showRecordPlay();
  void showRecordStop();
  void setRecorderTimer(String txt);
  void setPlayerTimer(String txt);
}
