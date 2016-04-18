package com.example.ankit.b_goals;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ankit on 13-03-2016.
 */
public class Signup extends Activity {
    DatabaseHelper helper=new DatabaseHelper(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void onSignUpClick(View v)
    {
        if(v.getId()==R.id.Bsignupbutton)
        {
            EditText name=(EditText)findViewById(R.id.TFname);
            EditText email=(EditText)findViewById(R.id.TFemail);
            EditText uname=(EditText)findViewById(R.id.TFuname);
            EditText pass1=(EditText)findViewById(R.id.TFpass1);
            EditText pass2=(EditText)findViewById(R.id.TFpass2);

            String namestr=name.getText().toString();
            String emailstr=email.getText().toString();
            String unamestr=uname.getText().toString();
            String pass1str=pass1.getText().toString();
            String pass2str=pass2.getText().toString();

            if(!pass1str.equals(pass2str))
            {
                //popup message
                Snackbar.make(v, "Passwords don't match!", Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            }
            else
            {
                //insert the details in database
                Contact c=new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);
                Toast signupmsg = Toast.makeText(Signup.this,"Signed up Successfully!",Toast.LENGTH_SHORT);
                signupmsg.show();
                Snackbar.make(v,"Signed Up Successfully!",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                finish();
            }
        }
    }
}
