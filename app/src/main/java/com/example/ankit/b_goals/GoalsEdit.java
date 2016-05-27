package com.example.ankit.b_goals;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.Button;

import java.util.Date;

/**
 * Created by Ankit on 17-04-2016.
 */

public class GoalsEdit extends Activity {

    Button checkin_date_btn , checkout_date_btn , checkin_time_btn , checkout_time_btn;
    int checkin_year , checkin_month , checkin_date , checkout_year , checkout_month , checkout_date;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goalsedit);
        showDialogOnButtonClick();
    }
    public void showDialogOnButtonClick(){
        checkin_date_btn = (Button)findViewById(R.id.datebutton);
        checkin_date_btn.setOnClickListener(
                new View.OnClickListener(){
                 @Override
                    public void onClick(View v) {
                     showDialog(DIALOG_ID);

                 }
                }
        );

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
        {
            return new DatePickerDialog(this,dpickerListener,checkin_year,checkin_month,checkin_date);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            checkin_year = year;
            checkin_month = monthOfYear;
            checkout_date = dayOfMonth;
            Toast.makeText(GoalsEdit.this, checkin_year + "/" + checkin_month + "/" + checkin_date, Toast.LENGTH_SHORT).show();

        }

    };


    public void onGSclick(View v)
    {
        if(v.getId()==R.id.Goalsbutton)
        {
            Toast temp = Toast.makeText(GoalsEdit.this, "Can be configured Later!", Toast.LENGTH_SHORT);
            temp.show();

        }

    }

}