package com.example.ankit.b_goals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ankit on 17-04-2016.
 */

public class BusinessRead extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussinessread);
    }
    public void onBclick(View v)
    {
        if(v.getId()==R.id.Bedit)
        {
            Intent i  = new Intent(BusinessRead.this,BusinessAdd_show.class);
            startActivity(i);
        }

    }




}