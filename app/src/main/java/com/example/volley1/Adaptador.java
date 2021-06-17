package com.example.volley1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context miContexto;
    ArrayList<Usuario> listaUsuarios;

    public Adaptador(Context miContexto, ArrayList<Usuario> listaUsuarios) {
        this.miContexto = miContexto;
        this.listaUsuarios = listaUsuarios;
    }


    @Override
    public int getCount() {
        return listaUsuarios.size();
    }

    @Override
    public Object getItem(int pos) {
        return listaUsuarios.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater inflador = LayoutInflater.from(miContexto);
        view=inflador.inflate(R.layout.itemlayout, null);

        ImageView imagen=view.findViewById(R.id.iv1);
        TextView nombre=view.findViewById(R.id.tvNombre);
        TextView email=view.findViewById(R.id.tvMail);
        //imagen.setImageResource(Integer.parseInt(listaUsuarios.get(pos).getFoto().toString()));

        nombre.setText(listaUsuarios.get(pos).getNombre());
        email.setText(listaUsuarios.get(pos).getMail());

        return view;
    }
}

