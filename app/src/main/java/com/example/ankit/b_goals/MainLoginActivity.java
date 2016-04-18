package com.example.ankit.b_goals;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainLoginActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainloginactivity);
    }
    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.Blogin)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(pass.equals(password)&&( password != null||password != ""))
            {
                int val = 1;
                Intent i=new Intent(MainLoginActivity.this,MainActivity.class);
                i.putExtra("check",val);
                Toast temp = Toast.makeText(MainLoginActivity.this,"You are now Signed In!",Toast.LENGTH_SHORT);
                temp.show();
                startActivity(i);
            }
            else{
                Toast temp = Toast.makeText(MainLoginActivity.this,"Username and Password don't match!",Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if(v.getId()==R.id.Bsignup)
        {
            Intent i=new Intent(MainLoginActivity.this,Signup.class);
            startActivity(i);
        }

    }

}
