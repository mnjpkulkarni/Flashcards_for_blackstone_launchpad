package com.mnjpk.flashcards.flashcards;

import android.view.View;

/**
 * Created by manojkulkarni on 9/17/17.
 */

public interface RecyclerClickListener {

    public void onItemClick(View v, int position);
    public void onItemLongClick(View v,int position);

}
