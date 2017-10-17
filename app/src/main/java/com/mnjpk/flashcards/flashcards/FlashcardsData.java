package com.mnjpk.flashcards.flashcards;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manojkulkarni on 9/17/17.
 */

public class FlashcardsData {

    List<Map<String,?>> flashcardsList;
    DatabaseReference databaseReference;
    Context mContext;
    RecyclerAdapter recyclerAdapter;

    public void setAdapter(RecyclerAdapter mAdapter) {
        recyclerAdapter = mAdapter;
    }

    public void removeItemFromServer(Map<String,?> flashCard){
        if(flashCard!=null){
            String id = (String)flashCard.get("id");
            databaseReference.child(id).removeValue();
        }
    }

    public void addItemToServer(Map<String,?> flashCard){
        if(flashCard!=null){
            String id = (String) flashCard.get("id");
            databaseReference.child(id).setValue(flashCard);
        }
    }

    public DatabaseReference getFireBaseRef(){
        return databaseReference;
    }

    public void setContext(Context context){mContext = context;}

    public List<Map<String, ?>> getFlashcardsList() {
        return flashcardsList;
    }

    public int getSize(){
        return flashcardsList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < flashcardsList.size()){
            return (HashMap) flashcardsList.get(i);
        } else return null;
    }

    public void onItemRemovedFromCloud(HashMap item){
        int position = -1;
        String id=(String)item.get("id");
        for(int i=0;i<flashcardsList.size();i++){
            HashMap flashcard = (HashMap)flashcardsList.get(i);
            String mid = (String) flashcard.get("id");
            if(mid.equals(id)){
                position= i;
                break;
            }
        }
        if(position != -1){
            flashcardsList.remove(position);
            Toast.makeText(mContext, "Item Removed:" + id, Toast.LENGTH_SHORT).show();

        }
    }

    public void onItemAddedToCloud(HashMap item){
        int insertPosition = 0;
        String id=(String)item.get("id");
        for(int i=0;i<flashcardsList.size();i++){
            HashMap hospital = (HashMap)flashcardsList.get(i);
            String mid = (String)hospital.get("id");
            if(mid.equals(id)){
                return;
            }
            if(mid.compareTo(id)<0){
                insertPosition=i+1;
            }else{
                break;
            }
        }
        flashcardsList.add(insertPosition,item);
        // Toast.makeText(mContext, "Item added:" + id, Toast.LENGTH_SHORT).show();

    }


    public void onItemUpdatedToCloud(HashMap item){
        String id=(String)item.get("id");
        for(int i=0;i<flashcardsList.size();i++){
            HashMap hospital = (HashMap)flashcardsList.get(i);
            String mid = (String)hospital.get("id");
            if(mid.equals(id)){
                flashcardsList.remove(i);
                flashcardsList.add(i,item);
                Log.d("My Test: NotifyChanged",id);

                break;
            }
        }

    }

    public void initializeDataFromCloud() {
        flashcardsList.clear();
       databaseReference.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Log.d("MyTest: OnChildAdded", dataSnapshot.toString());
                HashMap<String,String> flashCard = (HashMap<String,String>)dataSnapshot.getValue();
                onItemAddedToCloud(flashCard);
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Log.d("MyTest: OnChildChanged", dataSnapshot.toString());
                HashMap<String,String> flashCard = (HashMap<String,String>)dataSnapshot.getValue();
                onItemUpdatedToCloud(flashCard);
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Log.d("MyTest: OnChildRemoved", dataSnapshot.toString());
                HashMap<String,String> flashCard = (HashMap<String,String>)dataSnapshot.getValue();
                onItemRemovedFromCloud(flashCard);
            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public FlashcardsData(){

        flashcardsList = new ArrayList<Map<String,?>>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("flashcards").getRef();
        recyclerAdapter = null;
        mContext = null;

    }

    public int findFirst(String query){

        for(int i=0;i<flashcardsList.size();i++){
            HashMap hm = (HashMap)getFlashcardsList().get(i);
            if(((String)hm.get("word")).toLowerCase().contains(query.toLowerCase())){
                return i;
            }
        }
        return 0;

    }


}
