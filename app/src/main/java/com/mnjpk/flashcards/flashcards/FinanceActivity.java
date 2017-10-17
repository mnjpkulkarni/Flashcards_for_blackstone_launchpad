package com.mnjpk.flashcards.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class FinanceActivity extends AppCompatActivity {

    LinearLayout card1;
    TextView time;
    FlashcardsData flashcardsData;
    List<Map<String, ?>> flashcardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        time=(TextView)findViewById(R.id.time);

        flashcardsData=new FlashcardsData();

        card1=(LinearLayout)findViewById(R.id.card1);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinanceActivity.this,CardView.class);
                startActivity(intent);
            }
        });

    }
}
