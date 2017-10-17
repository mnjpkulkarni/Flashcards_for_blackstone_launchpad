package com.mnjpk.flashcards.flashcards;

import android.content.Context;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by manojkulkarni on 9/17/17.
 */

public class RecyclerAdapter extends FirebaseRecyclerAdapter<Flashcards,RecyclerAdapter.FlashcardViewHolder> {


    private Context mContext;
    static RecyclerClickListener recyclerClickListener;

    public RecyclerAdapter(Class<Flashcards> modelClass, int modelLayout, Class<FlashcardViewHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext=context;
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener)
    {
        this.recyclerClickListener=recyclerClickListener;

    }

    @Override
    protected void populateViewHolder(RecyclerAdapter.FlashcardViewHolder viewHolder, Flashcards model, int position) {
        viewHolder.word.setText(model.getWord());
        Log.d("RecyclerAdapter", "populateViewHolder:  actual printed is"+model.getWord() + " " +
                "but at position we need " + position);
    }


    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {

        public TextView word,grammar,definition;

        public FlashcardViewHolder(View itemView) {
            super(itemView);
            word=(TextView)itemView.findViewById(R.id.mainword);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerClickListener.onItemClick(v,getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerClickListener.onItemLongClick(v,getAdapterPosition());
                    return true;
                }
            });


        }
    }

}
