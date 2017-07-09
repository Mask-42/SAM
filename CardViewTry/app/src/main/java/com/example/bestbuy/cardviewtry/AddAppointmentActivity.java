package com.example.bestbuy.cardviewtry;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import android.text.format.DateFormat;
import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
EditText time,date;
    TextView phone_no;
    Button submit;

    int year,month,day,hours,minutes;
    int yearF,monthF,dayF,hoursF,minutesF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Bundle bundle=getIntent().getExtras();
        String Phone=bundle.getString("Phone");
        
        setTitle("Fix Appointment");
        time=(EditText)findViewById(R.id.Time);
        date=(EditText)findViewById(R.id.Date);
        phone_no=(TextView)findViewById(R.id.PhoneText);
        submit=(Button)findViewById(R.id.Submit1);


        phone_no.setText(Phone);
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Submit1:

                break;
            case R.id.Time:


                Calendar c1= Calendar.getInstance();
                hours=c1.get(Calendar.HOUR_OF_DAY);
                minutes=c1.get(Calendar.MINUTE);

                TimePickerDialog timePicker= new TimePickerDialog(AddAppointmentActivity.this,AddAppointmentActivity.this,hours,minutes, DateFormat.is24HourFormat(this));
                timePicker.show();
                break;
            case R.id.Date:

                Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker= new DatePickerDialog(AddAppointmentActivity.this,AddAppointmentActivity.this,year,month,day);

                datePicker.show();
                break;

        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        yearF=i;
        monthF=i1+1;
        dayF=i2;

        date.setText(String.valueOf(dayF)+"/"+String.valueOf(monthF)+"/"+String.valueOf(yearF));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hoursF=i;
        minutesF=i1;

        time.setText(String.valueOf(hoursF)+" :"+String.valueOf(minutesF));
    }
}
