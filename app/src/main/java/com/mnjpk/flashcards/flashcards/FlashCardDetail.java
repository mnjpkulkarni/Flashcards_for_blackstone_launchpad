package com.mnjpk.flashcards.flashcards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardDetail extends Fragment {

    static HashMap<String, ?> m;
    TextView word,grammar,definition;

    public FlashCardDetail() {
        m=new HashMap();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.fragment_flash_card_detail, container, false);
        setHasOptionsMenu(true);
        if(getArguments()!=null)
        {

            m=(HashMap) getArguments().getSerializable("flashcard");

        }

        word=(TextView)rootView.findViewById(R.id.word);
        grammar=(TextView)rootView.findViewById(R.id.grammar);
        definition=(TextView)rootView.findViewById(R.id.definition);

        word.setText((String) m.get("word"));
        grammar.setText((String) m.get("grammar"));
        definition.setText((String) m.get("definition"));


        return rootView;

    }

    public static FlashCardDetail newInstance(HashMap<String,?> flashcardmap) {
        FlashCardDetail flashCardDetail=new FlashCardDetail();
        m=flashcardmap;
        Bundle args=new Bundle();
        args.putSerializable("flashcard",flashcardmap);
        flashCardDetail.setArguments(args);
        return  flashCardDetail;
    }



}
