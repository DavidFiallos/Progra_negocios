package com.negocios.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private TextView tv;
    private  Random rand;
    EditText ptnombre;
    EditText ptedad;
    EditText alergias;
    RadioGroup radioGroup;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private JSONArray ImmuniD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
         tv = (TextView) findViewById(R.id.txt1);
         generar();

        ptnombre = findViewById(R.id.ptnombre);
        ptedad = findViewById(R.id.ptedad);
        alergias = findViewById(R.id.et3);
        radioGroup = (RadioGroup)findViewById(R.id.rdbGenero);

        preference = getSharedPreferences("Immuni", MODE_PRIVATE);
        editor = preference.edit();

        try {
            ImmuniD = new JSONArray(preference.getString("data", "[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void generar(){

        tv.setText(String.valueOf(rand.nextInt(2000 - 1000)+1000));

    }

    public void enviar(View view){

        String id = tv.getText().toString();
        String Nombre = ptnombre.getText().toString();
        String Edad = ptedad.getText().toString();
        String Genero = "";
        String Alergias = alergias.getText().toString();
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1)  {
        }
        else{ if (checkedRadioButtonId == R.id.radioButtonM) {
            Genero = "Masculino";
        }else{Genero = "femenino";} }

        JSONObject ImmuniItems = new JSONObject();
        try {
            ImmuniItems.put("ID", id);
            ImmuniItems.put("Nombre", Nombre);
            ImmuniItems.put("Edad", Edad);
            ImmuniItems.put("Genero", Genero);
            ImmuniItems.put("Enfermedades/Alergias", Alergias);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ImmuniD.put(ImmuniItems);

        editor.putString("data", ImmuniD.toString());
        editor.commit();

        ptnombre.setText("");
        ptedad.setText("");


        Intent miIntent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(miIntent);
    }



}
