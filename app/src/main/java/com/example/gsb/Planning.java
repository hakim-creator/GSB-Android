package com.example.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Planning extends AppCompatActivity {

    SQLiteDataBaseHelper db2;
    TextView textListe;
    Spinner spinnerrdv;
    int idSelect;
    EditText idInputrdv;

    ArrayList<String>lesrdv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityplanning_main);
        db2 = new SQLiteDataBaseHelper(this);

        spinnerrdv= findViewById(R.id.spinnerrdv);
        idInputrdv=(EditText) findViewById(R.id.editTextIdSuprdv);




        lesrdv = new ArrayList<>();
        majListeRDV();


        ArrayAdapter<String> pro = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lesrdv);
        pro.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerrdv.setAdapter(pro);

        spinnerrdv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void clicAfficherRDV(View view) { majListeRDV(); }

    public void majListeRDV() {
        try {
            lesrdv.clear();
            Cursor data = db2.getAllData2();

            while (data.moveToNext()) {
                lesrdv.add( String.valueOf("n°"+" "+ data.getInt(0)+" "+ "le"+" "+
                        data.getString(1) +" "+"à"+" "+ data.getString(2) +" "+"heure"+" "+"avec le"+" "+ data.getString(3)));



            }

        } catch (Exception e) {
            textListe.setText(e.getMessage());
        }
    }


    public void deleterdvClic(View view) {
        lesrdv.clear();
        String idS=idInputrdv.getText().toString();
        db2.deleteDatardv(Integer.parseInt(idS));
        majListeRDV();

    }

    public void clicVoirPlanning(View view) {
        Intent intentPlanningJour = new Intent(this, MainPlanningJour.class);
        startActivity(intentPlanningJour);
    }
}
