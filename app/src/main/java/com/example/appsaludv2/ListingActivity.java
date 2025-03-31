package com.example.appsaludv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.appsaludv2.Adapters.PacienteAdapter;

import DataAccess.DAOPaciente;

public class ListingActivity extends AppCompatActivity {

    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        layoutInflater = getLayoutInflater();

        ListView lvListado = findViewById(R.id.lvListado);
        listado(lvListado);

        Toolbar tbListMenu = findViewById(R.id.tbListMenu);
        setSupportActionBar(tbListMenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void listado(ListView lvListado) {
        DAOPaciente daoPaciente = new DAOPaciente();
        daoPaciente.Cargar(this);

        if (daoPaciente.getSize() < 0) {
            aviso();
            return;
        }
        lvListado.setAdapter(new PacienteAdapter(daoPaciente, getApplicationContext()));
    }

    private void aviso() {
        View viewLayout = layoutInflater.inflate(R.layout.item_toast_layout, null);

        ImageView toast_icon = viewLayout.findViewById(R.id.toast_icon);
        TextView toast_text = viewLayout.findViewById(R.id.toast_text);
        toast_icon.setImageResource(R.drawable.alert);
        toast_text.setText("No se encontrarón pacientes registrados");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewLayout);
        toast.show();
    }
}