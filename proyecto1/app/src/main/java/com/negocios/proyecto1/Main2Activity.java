package com.negocios.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class Main2Activity extends AppCompatActivity {

    EditText ListaItems;
    private RatingBar barra;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private JSONArray listJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListaItems = findViewById(R.id.ListaItems);

        this.preferences = getSharedPreferences("Immuni", MODE_PRIVATE);

        try {
            listJson = new JSONArray(this.preferences.getString("data", "[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i<listJson.length(); i++){
            try {
                ListaItems.append("ID: " + listJson.getJSONObject(i).getString("ID").toString()+"\nNombre: " + listJson.getJSONObject(i).getString("Nombre").toString()+ "\nEdad:  " + listJson.getJSONObject(i).getString("Edad").toString()+"\nGenero:"+listJson.getJSONObject(i).getString("Genero").toString()+"\nEnfermedades/Alergias: " + listJson.getJSONObject(i).getString("Enfermedades/Alergias").toString()+"\n\t -----------\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        barra = findViewById(R.id.ratingBar);
        barra.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(Main2Activity.this,"Usted ha votado con : "+v,Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void atras(View view){
        Intent miIntent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(miIntent);
    }
}
