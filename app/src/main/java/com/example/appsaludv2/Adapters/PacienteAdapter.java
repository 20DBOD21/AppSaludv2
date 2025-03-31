package com.example.appsaludv2.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsaludv2.R;

import java.util.List;

import DataAccess.DAOPaciente;
import Models.Paciente;

public class PacienteAdapter extends BaseAdapter {
    //private List<Paciente> listAdapterPacientes;
    private DAOPaciente daoPaciente;
    private Context context;
    private LayoutInflater layoutInflater;

    public PacienteAdapter(DAOPaciente daoPaciente, Context context) {
        this.daoPaciente = daoPaciente;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return daoPaciente.getSize();
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

        Paciente pacienteView = daoPaciente.getPaciente(position);

        //ivPacientIcon.setImageURI(pacienteView.getFoto());
        ivPacientIcon.setImageBitmap(bitmapConvert(pacienteView.getFoto()));
        tvNames.setText(pacienteView.nombreCompleto());
        tvWeight.setText(pacienteView.pesoPersona());
        tvAge.setText(pacienteView.edadPersona());
        ivPacientSex.setImageResource(pacienteView.getGenero().equals("Femenino") ? R.drawable.female_symbol : R.drawable.male_symbol);
        tvPacientCity.setText("Ciudad: " + pacienteView.getGenero());

        return viewPaciente;
    }

    private Bitmap bitmapConvert(byte[] foto) {
        return BitmapFactory.decodeByteArray(foto, 0, foto.length);
    }
}
