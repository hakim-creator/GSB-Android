package com.example.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainPlanningJour extends AppCompatActivity {

    CalendarView calendarViewPlanning;
    String curDate;
    Spinner spinner;
    int idSelect;
    ArrayList<String>afficherPlanning;
    SQLiteDataBaseHelper db;
    TextView textListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityplanningaujourdhui_main);
        db  = new SQLiteDataBaseHelper(this);
        spinner = findViewById(R.id.spinnerplanningjour);

        calendarViewPlanning = findViewById(R.id.calendarViewPlanning);
        /**
         * Calendar planning du jour
         */
        calendarViewPlanning.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
/**
 * Instaciation de la collection
 */
        afficherPlanning = new ArrayList<>();

    }

    /**
     *Bouton voir le planning du jour
     * @param view
     */
    public void clicButtonPlanningJour(View view) {
        try {
            afficherPlanning.clear();
            Cursor data = db.getAllDataPlanning(curDate);

            while (data.moveToNext()) {
                afficherPlanning.add( String.valueOf("n°"+" "+ data.getInt(0)+" "+ "le"+" "+
                        data.getString(1) +" "+"à"+" "+ data.getString(2) +" "+"heure"+" "+"avec le"+" "+ data.getString(3)));



            }
            /**
             * Adaptateur du spinner Afficher Planning du jour
             */
            ArrayAdapter<String> rdvjour = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, afficherPlanning);
            rdvjour.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(rdvjour);
/**
 * Configuration Spinner pour Afficher Planning du jour
 */
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    idSelect = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });






        } catch (Exception e) {
            textListe.setText(e.getMessage());
        }

    }


}
