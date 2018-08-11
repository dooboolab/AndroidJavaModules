package com.dooboolab.androidjavamodules.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dooboolab.androidjavamodules.R;

public class MainActivity extends AppCompatActivity implements MainView {
  private final String TAG = "MainActivity";
  private MainPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    presenter = new MainPresenterImpl(this);
    presenter.initView();
  }

  @Override
  public void initView() {
    ListView listView = findViewById(R.id.list_view);

    final String[] values = new String[] {
        "Audio",
    };
    final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (values[0].equals("Audio")) {

          presenter.openAudioActivity(MainActivity.this);
        }
      }
    });
  }
}
