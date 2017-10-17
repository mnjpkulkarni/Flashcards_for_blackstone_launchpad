package com.mnjpk.flashcards.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddCardActivity extends AppCompatActivity {

    TextView addWord,addGrammar,addDefinition;
    Button addCardBtn,cancelCardBtn;

    String word,grammar,definition;

    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        addWord=(TextView)findViewById(R.id.editword);
        addGrammar=(TextView)findViewById(R.id.editgrammar);
        addDefinition=(TextView)findViewById(R.id.editdefinition);

        addCardBtn=(Button)findViewById(R.id.addcardbtn);
        cancelCardBtn=(Button)findViewById(R.id.cancelcardbtn);


        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard();
            }
        });

        cancelCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCard();
            }
        });

    }

    public void createCard() {
        word=addWord.getText().toString();
        grammar=addGrammar.getText().toString();
        definition=addDefinition.getText().toString();

        databaseReference1= FirebaseDatabase.getInstance().getReference().child("flashcards").getRef();

        Map<String,Object> cardMap=new HashMap<String, Object>();
        Map<String, Object> x=new HashMap<String, Object>();

        String id=word;
        x.put("id",id);
        x.put("word",word);
        x.put("grammar",grammar);
        x.put("definition",definition);

        cardMap.put(word,x);

        databaseReference1.updateChildren(cardMap);
        Toast.makeText(AddCardActivity.this,"A new card has been added",Toast.LENGTH_SHORT).show();
        Intent backToCardview=new Intent(AddCardActivity.this,CardView.class);
        startActivity(backToCardview);
    }

    public void cancelCard() {
        addWord.setText("");
        addGrammar.setText("");
        addDefinition.setText("");
    }

}
