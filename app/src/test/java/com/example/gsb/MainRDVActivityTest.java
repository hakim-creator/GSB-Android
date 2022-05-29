package com.example.gsb;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


@RunWith(RobolectricTestRunner.class)
public class MainRDVActivityTest {

    Context c;
    @Before
    public void setUp() throws Exception{
        c = RuntimeEnvironment.application;
    }






    @Test
    public void insert_isCorrect(){
        SQLiteDataBaseHelper db = new SQLiteDataBaseHelper(c);
        int nb = 0;
        db.insertDataRDV("2023","2","hakim");
        Cursor c2 = db.getAllData2();
        int nb2 = c2.getCount();
        assertEquals(nb+1, nb2);
    }

    @Test
    public void insert_isNotCorrect(){
        SQLiteDataBaseHelper db = new SQLiteDataBaseHelper(c);
        int nb = 0;
        db.insertDataRDV("2023","2","hakim");
        Cursor c2 = db.getAllData2();
        int nb2 = c2.getCount();
        assertEquals(nb, nb2);
    }
}