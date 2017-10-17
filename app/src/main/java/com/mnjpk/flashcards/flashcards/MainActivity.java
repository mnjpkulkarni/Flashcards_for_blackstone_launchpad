package com.mnjpk.flashcards.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView allLessons;
    Button finance,marketing,legal,productDevelopment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allLessons=(TextView)findViewById(R.id.alllessons);

        finance=(Button)findViewById(R.id.fin);
        marketing=(Button)findViewById(R.id.mar);
        legal=(Button)findViewById(R.id.leg);
        productDevelopment=(Button)findViewById(R.id.prodev);

        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFinance();
            }
        });

        marketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMarketing();
            }
        });

        legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLegal();
            }
        });

        productDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDevelopment();
            }
        });

    }

    public void goToFinance() {

        Intent intent=new Intent(MainActivity.this,FinanceActivity.class);
        startActivity(intent);
    }

    public void goToMarketing() {

    }

    public void goToLegal() {

    }

    public void goToProductDevelopment() {

    }
}
