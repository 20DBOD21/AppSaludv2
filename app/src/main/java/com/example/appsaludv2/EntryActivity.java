package com.example.appsaludv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import Models.Paciente;

public class EntryActivity extends AppCompatActivity {

    //public static ArrayList<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Toolbar toolbar = findViewById(R.id.tbEntryMenu);
        setSupportActionBar(toolbar);
        TextView TVIngresos = findViewById(R.id.tvIngresos);

        SharedPreferences preferences = getSharedPreferences("control", Context.MODE_PRIVATE);
        int ingresos = preferences.getInt("contador", 1);
        String fecha = preferences.getString("ultimafecha", "Nunca");

        String mensaje = "N°Ingresos: " + ingresos + "\nUltima Fecha: " + fecha;
        TVIngresos.setText(mensaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        if (item.getItemId() == R.id.itemSalir) {
            finish();
        }

        if (item.getItemId() == R.id.itemRegistrar) {
            intent = new Intent(EntryActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.itemListar) {
            intent = new Intent(EntryActivity.this, ListingActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.itemListaLLamadas) {
            intent = new Intent(EntryActivity.this, CallingActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}