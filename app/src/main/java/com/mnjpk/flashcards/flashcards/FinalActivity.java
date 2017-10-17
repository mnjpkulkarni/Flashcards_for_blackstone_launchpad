package com.mnjpk.flashcards.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalActivity extends AppCompatActivity {

    Button restart,nextDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        restart=(Button)findViewById(R.id.restart);
        nextDeck=(Button)findViewById(R.id.nextdeck);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinalActivity.this,CardView.class);
                startActivity(intent);
            }
        });

        nextDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinalActivity.this,FinanceActivity.class);
                startActivity(intent);
            }
        });
    }
}
