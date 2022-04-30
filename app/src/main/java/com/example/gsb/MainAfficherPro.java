package com.example.gsb;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainAfficherPro extends AppCompatActivity {
    SQLiteDataBaseHelper db;
    TextView textListe;
    EditText nomInput, prenomInput, adresseInput, code_postaleInput, villeInput, emailInput, telephoneInput, idInput;
    Spinner spinner;
    int idSelect;
    ArrayList<String> ajouterpro;
    ArrayAdapter<String> pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityafficherpro_main);
        /**
         * Association des variables
         */
        db = new SQLiteDataBaseHelper(this);
        nomInput = (EditText) findViewById(R.id.edittextNom);
        prenomInput = (EditText) findViewById(R.id.edittextPrenom);

        adresseInput = (EditText) findViewById(R.id.editTextTextPostalAddress);
        code_postaleInput = (EditText) findViewById(R.id.edittextCodePostal);
        villeInput = (EditText) findViewById(R.id.edittextVille);
        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        telephoneInput = (EditText) findViewById(R.id.editTextPhone);


        idInput=(EditText) findViewById(R.id.editTextIdSup);

        spinner = findViewById(R.id.spinner);
        ajouterpro = new ArrayList<>();
        majListe();

        /**
         * Afficher spinner
         */
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

    public void clicAfficherpro(View view) {
        majListe();
    }

    /**
     * Afficher les professionnel
     */
    public void majListe() {
        try {
            ajouterpro.clear();
            Cursor data = db.getAllData();

            while (data.moveToNext()) {
                ajouterpro.add( String.valueOf("n°"+data.getInt(0)+" "+"Mr/Mdme :"+" "+
                        data.getString(1) +" "+ data.getString(2)) );



            }

        } catch (Exception e) {
            textListe.setText(e.getMessage());
        }
        }

    /**
     *Bouton supprimer professionnel
     * @param view
     */
    public void deleteClic(View view){

        String idS=idInput.getText().toString();
        db.deleteData(Integer.parseInt(idS));
        pro.clear();
majListe();

    }

    /**
     * Bouton rechercher profession par ville
     * @param view
     */
    public void clicrecherche(View view) {
        EditText searshview = findViewById(R.id.searshview);
        if (searshview.getText().toString() != "") {
            try {
            pro.clear();
                Cursor data = db.getAllDataSearch(searshview.getText().toString());

                while (data.moveToNext()) {
                    ajouterpro.add(String.valueOf("n°" + data.getInt(0) + " " + "Mr/Mdme :" + " " +
                            data.getString(1) + " " + data.getString(2)));


                }

            } catch (Exception e) {
                textListe.setText(e.getMessage());
            }
        }
    }
}
