package com.example.gsb;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainRDVActivity extends AppCompatActivity {


    CalendarView calendarView;
    String curDate;

    Spinner spinner;
    int idSelect;
    String[] professionconcerner = {"chirurgien(ne)", "pharmacien(ne)", "Medecin generaliste", "psychologue"};
    SQLiteDataBaseHelper db2;
    EditText  uneHeure;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityrdv_main);


        calendarView = findViewById(R.id.CalendarViewDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String lesmois ="";
                if(month == 0)
                {
                    lesmois = "Janvier";
                }
                else
                if(month == 1)
                {
                    lesmois = "Fevrier";
                }
                else
                if(month == 2)
                {
                    lesmois = "Mars";
                }
                else
                if(month == 3)
                {
                    lesmois = "Avril";
                }
                else
                if(month == 4)
                {
                    lesmois = "Mai";
                }
                else
                if(month == 5)
                {
                    lesmois = "Juin";
                }
                else
                if(month == 6)
                {
                    lesmois = "Juillet";
                }
                else
                if(month == 7)
                {
                    lesmois = "Aout";
                }
                else
                if(month == 8)
                {
                    lesmois = "Septembre";
                }
                else
                if(month == 9)
                {
                    lesmois = "Octobre";
                }
                else
                if(month == 10)
                {
                    lesmois = "Novembre";
                }
                else
                {
                    lesmois = "Decembre";
                }

                curDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(lesmois) + "/" + String.valueOf(year);
            }
        });

        db2 = new SQLiteDataBaseHelper(this);


        uneHeure = (EditText) findViewById(R.id.editTextTime);
        spinner =  findViewById(R.id.spinnerproconcerner);

        ArrayAdapter<String> aaLangages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, professionconcerner);
        aaLangages.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(aaLangages);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    public void clicPrendreRDV(View view) {
        db2.insertDataRDV(curDate, uneHeure.getText().toString(), professionconcerner[idSelect]);
    }
}
