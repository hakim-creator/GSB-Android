package com.example.gsb;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainProfessionnel extends AppCompatActivity {
    SQLiteDataBaseHelper db;
    EditText nomInput, prenomInput, adresseInput, code_postaleInput, villeInput, emailInput, telephoneInput;
    Spinner spinner;
    String[] typeprofessionnel = {"chirurgien(ne)", "pharmacien(ne)", "Medecin generaliste", "psychologue"};
    int idSelect;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityajouterpro_main);
        db = new SQLiteDataBaseHelper(this);

        nomInput = (EditText) findViewById(R.id.edittextNom);
        prenomInput = (EditText) findViewById(R.id.edittextPrenom);
        spinner = findViewById(R.id.spinnertypepro);
        adresseInput = (EditText) findViewById(R.id.editTextTextPostalAddress);
        code_postaleInput = (EditText) findViewById(R.id.edittextCodePostal);
        villeInput = (EditText) findViewById(R.id.edittextVille);
        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        telephoneInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
/**
 * Adaptateur spinner affiher type professionnel
 */
        ArrayAdapter<String> aaLangages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeprofessionnel);
        aaLangages.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(aaLangages);
/**
 * Configuration du spinner
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

    }

    /**
     * Bouton Enregistrer pro
     * @param view
     */

    public void clicEnregistrerPro(View view) {

        db.insertData( nomInput.getText().toString(), prenomInput.getText().toString(), typeprofessionnel[idSelect], adresseInput.getText().toString(), code_postaleInput.getText().toString(), villeInput.getText().toString(), emailInput.getText().toString(), telephoneInput.getText().toString());
    }




}
