package com.example.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainRDVActivity extends AppCompatActivity {


    CalendarView calendarView;
    String curDate;
    SQLiteDataBaseHelper db;
    Spinner spinner;
    int idSelect;
    TextView textListe;
   // String[] professionconcerner = {"chirurgien(ne)", "pharmacien(ne)", "Medecin generaliste", "psychologue"};
    ArrayList<String> ajouterpro;
    ArrayAdapter<String> pro;
    SQLiteDataBaseHelper db2;
    EditText  uneHeure;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityrdv_main);


        calendarView = findViewById(R.id.CalendarViewDate);
        /**
         * Calandar prendre rdv
         */
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
        ajouterpro = new ArrayList<>();
        majListe();
/**
 * Spinner afficher type pro
 */
        //ArrayAdapter<String> aaLangages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ajouterpro);
        //aaLangages.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //spinner.setAdapter(aaLangages);
        pro = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ajouterpro);
        pro.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(pro);

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
    public void majListe() {
        try {
            ajouterpro.clear();
            Cursor data = db2.getAllData();

            while (data.moveToNext()) {
                ajouterpro.add( String.valueOf("Mr/Mdme :"+" "+
                        data.getString(1) +" "+ data.getString(2)+" "+"("+ data.getString(3)+")") );



            }

        } catch (Exception e) {
            textListe.setText(e.getMessage());
        }
    }
    /**
     * Bouton prendre rdv
     * @param view
     */

    public void clicPrendreRDV(View view) {
        db2.insertDataRDV(curDate, uneHeure.getText().toString(), ajouterpro.get(idSelect));
    }
}
