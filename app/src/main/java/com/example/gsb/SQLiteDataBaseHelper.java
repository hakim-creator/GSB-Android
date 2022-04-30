package com.example.gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GSB3.db";
    public static final String TABLE_NAME = "professionnel";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOM";
    public static final String COL_3 = "PRENOM";
    public static final String COL_4 = "profession";
    public static final String COL_5 = "adresse";
    public static final String COL_6 = "code_postale";
    public static final String COL_7 = "ville";
    public static final String COL_8 = "email";
    public static final String COL_9 = "telephone";
    public static final String TABLE_NAMERDV = "rdv";
    public static final String COL_10 = "ID";
    public static final String COL_11 = "LADATE";
    public static final String COL_12 = "HEURE";
    public static final String COL_13 = "PRO";


    public SQLiteDataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, PRENOM TEXT, profession TEXT, adresse TEXT, code_postale TEXT, ville TEXT, email TEXT, telephone TEXT)");

        db.execSQL("CREATE table " + TABLE_NAMERDV + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, LADATE TEXT, HEURE TEXT, PRO TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMERDV);
        onCreate(db);
    }

    /**
     * Inserer un professionnel
     * @param nom
     * @param prenom
     * @param profession
     * @param adresse
     * @param code_postale
     * @param ville
     * @param email
     * @param telephone
     */
    public void insertData( String nom, String prenom, String profession, String adresse, String code_postale, String ville, String email, String telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nom);
        contentValues.put(COL_3, prenom);
        contentValues.put(COL_4, profession);
        contentValues.put(COL_5, adresse);
        contentValues.put(COL_6, code_postale);
        contentValues.put(COL_7, ville);
        contentValues.put(COL_8, email);
        contentValues.put(COL_9, telephone);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    /**
     * Inserer rdv
     * @param uneDate
     * @param uneHeure
     * @param unPro
     */
    public void insertDataRDV(String uneDate, String uneHeure, String unPro)
    {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_11, uneDate);
        contentValues.put(COL_12, uneHeure);
        contentValues.put(COL_13, unPro);

        db2.insert(TABLE_NAMERDV,null,contentValues);
        db2.close();

    }

    /**
     * Afficher les professionnel
     * @return
     */
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME, null);
        return result;

    }

    /**
     * Afficher le planning du jour
     * @param uneDate
     * @return
     */
    public Cursor getAllDataPlanning(String uneDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAMERDV + " Where LADATE Like '%"+ uneDate + "%'", null);
        return result;

    }

    /**
     * Afficher professionel par ville
     * @param search
     * @return
     */
    public Cursor getAllDataSearch(String search){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME+ " Where Ville Like '%"+ search + "%'", null);
        return result;

    }

    /**
     * Afficher les rdv
     * @return
     */
    public Cursor getAllData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAMERDV, null);
        return result;

    }

    /**
     * Supprimer les professionnel
     * @param id
     */
    public void deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL_1+"="+id,null);

        db.close();
    }

    /**
     * Supprimer les rdv
     * @param id
     */
    public void deleteDatardv(int id){
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.delete(TABLE_NAMERDV,COL_10+"="+id,null);

        db2.close();
    }

    /**
     * Modifier un rdv
     * @param id
     * @param uneDate
     * @param uneHeure
     * @param unPro
     */
    public void updateDatardv(int id,String uneDate, String uneHeure, String unPro)
    {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_11, uneDate);
        contentValues.put(COL_12, uneHeure);
        contentValues.put(COL_13, unPro);

        db2.update(TABLE_NAMERDV,contentValues, COL_10+"="+id, null);
        db2.close();

    }





}

