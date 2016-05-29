package com.example.ankit.b_goals;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ankit on 17-04-2016.
 */

public class GoalsEdit extends Activity {

    Button checkin_date_btn , checkout_date_btn , checkin_time_btn , checkout_time_btn;
    public int Year , Month , Date ;
    public int Hour , Min ;
    boolean full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goalsedit);

        final Calendar cal = Calendar.getInstance();
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH);
        Date = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
    }
    public void showDialogOnButtonClick(){
        checkin_date_btn = (Button)findViewById(R.id.datebutton);
        checkout_date_btn = (Button)findViewById(R.id.datebutton2);
        checkin_time_btn = (Button)findViewById(R.id.timebutton);
        checkout_time_btn = (Button)findViewById(R.id.timebutton2);

        checkin_time_btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(2);
                    }
                }
        );
        checkout_time_btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(3);
                    }
                }
        );

        checkout_date_btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        showDialog(1);
                    }
                }
        );

        checkin_date_btn.setOnClickListener(
                new View.OnClickListener(){
                 @Override
                    public void onClick(View v) {
                     showDialog(0);
                 }
                }
        );

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == 0)
        {
            return new DatePickerDialog(this , checkin_dpickerListener , Year , Month , Date);
        }
        if(id == 1)
        {
            return new DatePickerDialog(this , checkout_dpickerListener , Year , Month , Date);
        }
        if(id == 2)
        {
            return new TimePickerDialog(this ,checkin_tpickerListener, Hour , Min , full );
        }
        if(id == 3)
        {
            return new TimePickerDialog(this ,checkout_tpickerListener , Hour , Min , full);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener checkin_tpickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Hour = hourOfDay;
            Min = minute;
            checkin_time_btn = (Button)findViewById(R.id.timebutton);
            checkin_time_btn.setText( Hour + ":" + Min );
            Toast.makeText(GoalsEdit.this, Hour + ":" + Min , Toast.LENGTH_SHORT).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener checkout_tpickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Hour = hourOfDay;
            Min = minute;
            checkout_time_btn = (Button)findViewById(R.id.timebutton2);
            checkout_time_btn.setText( Hour + ":" + Min );
            Toast.makeText(GoalsEdit.this, Hour + ":" + Min , Toast.LENGTH_SHORT).show();
        }
    };


    private DatePickerDialog.OnDateSetListener checkin_dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Year = year;
            Month = monthOfYear + 1;
            Date = dayOfMonth;
            checkin_date_btn = (Button)findViewById(R.id.datebutton);
            checkin_date_btn.setText( Date + "/" + Month + "/" + Year);
            Toast.makeText(GoalsEdit.this, Date + "/" + Month + "/" + Year, Toast.LENGTH_SHORT).show();
        }

    };
    private DatePickerDialog.OnDateSetListener checkout_dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Year = year;
            Month = monthOfYear + 1;
            Date = dayOfMonth;
            checkout_date_btn = (Button)findViewById(R.id.datebutton2);
            checkout_date_btn.setText( Date + "/" + Month + "/" + Year);
            Toast.makeText(GoalsEdit.this, Date + "/" + Month + "/" + Year, Toast.LENGTH_SHORT).show();
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