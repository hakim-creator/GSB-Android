package com.example.gsb;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainModifRdv extends AppCompatActivity {

    SQLiteDataBaseHelper db2;
    EditText NumModif, DateModif, HeureModif;
    Spinner TypeModif;
    int idSelect;
    String[] professionconcerner = {"chirurgien(ne)", "pharmacien(ne)", "Medecin generaliste", "psychologue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_rdv);
        db2 = new SQLiteDataBaseHelper(this);

        NumModif = (EditText) findViewById(R.id.EditTextNumeroModif);
        DateModif = (EditText) findViewById(R.id.EditTextDateModif);
        HeureModif = (EditText) findViewById(R.id.EditTextHeureModif);
        TypeModif =  findViewById(R.id.spinnerproModif);

        /**
         * Spinner Choix du professionnel
         */
        ArrayAdapter<String> aaLangages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, professionconcerner);
        aaLangages.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        TypeModif.setAdapter(aaLangages);

        TypeModif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


}

    /**
     *Bouton Modifier rdv
     * @param view
     */
    public void clicModifierRdv(View view) {
        String idS=NumModif.getText().toString();
        db2.updateDatardv(Integer.parseInt(idS),DateModif.getText().toString(),HeureModif.getText().toString(),professionconcerner[idSelect]);
    }


    }
