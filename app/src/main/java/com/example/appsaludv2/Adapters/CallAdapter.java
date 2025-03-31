package com.example.appsaludv2.Adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsaludv2.R;

import DataAccess.DAOCalling;

public class CallAdapter extends BaseAdapter {
    private DAOCalling daoCalling;
    private Context context;
    private LayoutInflater layoutInflater;

    public CallAdapter(DAOCalling daoCalling, Context context) {
        this.daoCalling = daoCalling;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return daoCalling.getSize();
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
        View viewCall = layoutInflater.inflate(R.layout.item_call_view, null);

        ImageView ivCallIcon = viewCall.findViewById(R.id.ivCallIcon);
        TextView tvNumber = viewCall.findViewById(R.id.tvNumber);
        TextView tvDate = viewCall.findViewById(R.id.tvDate);
        TextView tvTime = viewCall.findViewById(R.id.tvTime);

        Pair<Integer, String> numero = daoCalling.getCall(position);
        int tipoIndex = numero.first;
        String numeroIndex = numero.second;

        ivCallIcon.setImageResource(
                tipoIndex == 1 ? R.drawable.incoming :
                        tipoIndex == 2 ? R.drawable.outcoming :
                                tipoIndex == 5 ? R.drawable.block : R.drawable.image_circle_background
                );
        tvNumber.setText(numeroIndex);

        return viewCall;
    }
}
