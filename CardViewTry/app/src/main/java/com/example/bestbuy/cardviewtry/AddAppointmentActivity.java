package com.example.bestbuy.cardviewtry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Bundle bundle=getIntent().getExtras();
        String Phone=bundle.getString("Phone");
        setTitle(Phone);
    }
}
