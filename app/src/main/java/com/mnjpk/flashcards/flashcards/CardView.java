package com.mnjpk.flashcards.flashcards;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class CardView extends AppCompatActivity  {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter recyclerAdapter;
    FlashcardsData flashcardsData;
    List<Map<String, ?>> flashcardsList;
    Flashcards flashcards;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    Context mContext;
    DividerItemDecoration dividerItemDecoration;
    Button addcard,finish;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        flashcardsData=new FlashcardsData();
        recyclerView=(RecyclerView)findViewById(R.id.cardView);
        flashcardsList=flashcardsData.getFlashcardsList();
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        addcard=(Button)findViewById(R.id.addcard);
        finish=(Button)findViewById(R.id.finish);
        time=(TextView)findViewById(R.id.time);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("flashcards").getRef();
        recyclerAdapter=new RecyclerAdapter(Flashcards.class,R.layout.fragment_card_list,RecyclerAdapter.FlashcardViewHolder.class,databaseReference,getApplicationContext());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(recyclerAdapter);
        recyclerView.setAdapter(alphaAdapter);



        if(flashcardsData.getSize()==0)
        {
            flashcardsData.setAdapter(recyclerAdapter);
            flashcardsData.setContext(getApplicationContext());
            flashcardsData.initializeDataFromCloud();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        //collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);



        recyclerAdapter.setRecyclerClickListener(new RecyclerClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                final HashMap<String, ?> flashcard=(HashMap<String, ?>) flashcardsData.getItem(position);
                String id=(String) flashcard.get("id");
                Log.d("onItemClick", "id for clicked item is "+ position);
                Log.d("fuck", "onItemClick: "+id);
                Log.d("onItemClick", "id for clicked item is "+ flashcard.get("grammar").toString());
                DatabaseReference ref=flashcardsData.getFireBaseRef();
                time.setText(Double.toString(flashcardsData.getSize() * 0.25)+" min");
                // iterate

                ref.child(id).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //HashMap<String, Object>flashcard=(HashMap<String, Object>) dataSnapshot.getValue();
                        //Log.d("onItemClick", "id for clicked item is "+ position);
                        //Log.d("onItemClick", "id for clicked item is "+ flashcard.toString());
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_card_view, FlashCardDetail.newInstance(flashcard)).addToBackStack("null").commit();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("My Test","The Read failed: "+databaseError.getMessage());

                    }
                });
            }

            @Override
            public void onItemLongClick(View v, final int position) {
                PopupMenu popupMenu=new PopupMenu(CardView.this,v);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        HashMap<String,Object>flashcard;

                        switch (item.getItemId())
                        {
                            case R.id.delcontext:
                                flashcard=(HashMap) ((HashMap) flashcardsData.getItem(position)).clone();
                                flashcardsData.removeItemFromServer(flashcard);
                                return true;

                            default:
                                return false;
                        }
                    }
                });

                MenuInflater menuInflater=popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.contextual_menu,popupMenu.getMenu());
                popupMenu.show();

            }
        });

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CardView.this,AddCardActivity.class);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CardView.this,FinalActivity.class);
                startActivity(intent);
            }
        });

        //itemAnimation();

        OvershootInLeftAnimator animator = new OvershootInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());
// or recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.setItemAnimator(animator);

        recyclerView.getItemAnimator().setAddDuration(1000);
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.getItemAnimator().setMoveDuration(1000);
        recyclerView.getItemAnimator().setChangeDuration(1000);
    }


    public void itemAnimation() {
        //add item animation from wassbeef library
        //recyclerView.setItemAnimator();
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(2000);
        defaultItemAnimator.setRemoveDuration(2000);
        recyclerView.setItemAnimator(defaultItemAnimator);
        defaultItemAnimator.setChangeDuration(1000);
        defaultItemAnimator.getRemoveDuration();

    }

}
