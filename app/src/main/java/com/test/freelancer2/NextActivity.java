package com.test.freelancer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class NextActivity extends AppCompatActivity {
    ImageView  bg1,bg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        getSupportActionBar().hide();
        bg1=findViewById(R.id.bg);
        bg2=findViewById(R.id.bg2);
      int bgdata1=getIntent().getIntExtra("bg1",0);
        int bgdata2=getIntent().getIntExtra("bg2",R.drawable.e);

                bg1.setImageResource(bgdata1);
                bg2.setImageResource(bgdata2);

    }
}
