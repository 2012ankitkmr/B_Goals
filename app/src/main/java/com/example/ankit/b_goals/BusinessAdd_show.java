package com.example.ankit.b_goals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Ankit on 17-04-2016.
 */

public class BusinessAdd_show extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussiness_show);
    }

    public void onSclick(View v)
    {
       if(v.getId()==R.id.Bussinessbutton)
       {
           Toast temp = Toast.makeText(BusinessAdd_show.this, "Can be configured Later!", Toast.LENGTH_SHORT);
           temp.show();

       }

    }

}