package com.example.ankit.b_goals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Ankit on 17-04-2016.
 */

public class AddressEdit extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_edit);
    }

    public void onSclick(View v)
    {
        if(v.getId()==R.id.Addressbutton)
        {
            Toast temp = Toast.makeText(AddressEdit.this, "Can be configured Later!", Toast.LENGTH_SHORT);
            temp.show();

        }

    }

}