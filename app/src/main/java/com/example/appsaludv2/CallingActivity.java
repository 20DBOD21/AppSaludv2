package com.example.appsaludv2;

import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appsaludv2.Adapters.CallAdapter;

import java.util.List;

import DataAccess.DAOCalling;

public class CallingActivity extends AppCompatActivity {

    private Spinner spTipoLlamada;
    private String[] filtroLlamada = {
            "Filtrar por tipo",
            "Entrante",
            "Saliente",
            "Bloqueada"
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calling);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvListLlamadas = findViewById(R.id.lvListLlamadas);
        DAOCalling daoCalling = new DAOCalling();
        daoCalling.LlamadasListadas(this);

        spTipoLlamada = findViewById(R.id.spTipoLlamada);
        spTipoLlamada.setAdapter(new ArrayAdapter<>(
                getApplicationContext(), android.R.layout.simple_list_item_1, filtroLlamada
        ));

        lvListLlamadas.setAdapter(new CallAdapter(daoCalling, getApplicationContext()));

        spTipoLlamada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    daoCalling.LlamadasFiltradas(position);
                }
                if (position == 2) {
                    daoCalling.LlamadasFiltradas(position);
                }
                if (position == 3) {
                    daoCalling.LlamadasFiltradas(5);
                }
                lvListLlamadas.setAdapter(new CallAdapter(daoCalling, getApplicationContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}