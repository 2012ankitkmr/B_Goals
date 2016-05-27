package com.example.ankit.b_goals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ankit on 27-05-2016.
 */
public class GoalSwipeView extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goalswipeview);
    }

    public void onGclick(View v){
        if(R.id.Gbutton==v.getId())
        {
            Intent i = new Intent(GoalSwipeView.this,GoalsEdit.class);
            startActivity(i);

        }

    }




}
