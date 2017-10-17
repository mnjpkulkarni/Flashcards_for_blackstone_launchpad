package com.mnjpk.flashcards.flashcards;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by manojkulkarni on 9/17/17.
 */

public class FlashcardsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
