package com.example.appsaludv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsaludv2.R;

import java.util.List;

import Models.Paciente;

public class PacienteAdapter extends BaseAdapter {
    private List<Paciente> listAdapterPacientes;
    private Context context;
    private LayoutInflater layoutInflater;

    public PacienteAdapter(List<Paciente> listAdapterPacientes, Context context) {
        this.listAdapterPacientes = listAdapterPacientes;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listAdapterPacientes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewPaciente = layoutInflater.inflate(R.layout.item_user_view, null);

        ImageView ivPacientIcon = viewPaciente.findViewById(R.id.ivPacientIcon);
        TextView tvNames = viewPaciente.findViewById(R.id.tvNames);
        TextView tvWeight = viewPaciente.findViewById(R.id.tvWeight);
        TextView tvAge = viewPaciente.findViewById(R.id.tvAge);
        ImageView ivPacientSex = viewPaciente.findViewById(R.id.ivPacientSex);
        TextView tvPacientCity = viewPaciente.findViewById(R.id.tvPacientCity);

        Paciente pacienteView = listAdapterPacientes.get(position);

        ivPacientIcon.setImageURI(pacienteView.getFoto());
        tvNames.setText(pacienteView.nombreCompleto());
        tvWeight.setText(pacienteView.pesoPersona());
        tvAge.setText(pacienteView.edadPersona());
        ivPacientSex.setImageResource(pacienteView.getGenero().equals("Femenino") ? R.drawable.female_symbol : R.drawable.male_symbol);
        tvPacientCity.setText("Ciudad: " + pacienteView.getCiudad());

        return viewPaciente;
    }
}
