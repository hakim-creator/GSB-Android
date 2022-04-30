package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     *Bouton prendre rdv
     * @param view
     */
    public void ClicRDV(View view) {
        Intent prendrerdv = new Intent(this, MainRDVActivity.class);
        startActivity(prendrerdv);
    }

    /**
     *Bouton Voir planning
     * @param view
     */
    public void clicPlanning(View view) {
        Intent planning = new Intent(this, Planning.class);
        startActivity(planning);
    }

    /**
     *Bouton ajouter pro
     * @param view
     */
    public void clicPro(View view) {
        Intent intentPro = new Intent(this, MainProfessionnel.class);
        startActivity(intentPro);
    }


    /**
     *Bouton afficher pro
     * @param view
     */
    public void clicAfficherPro(View view) {
        Intent intentPro = new Intent(this, MainAfficherPro.class);
        startActivity(intentPro);
    }
}