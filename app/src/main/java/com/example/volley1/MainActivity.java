package com.example.volley1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Usuario> lista;
    private RequestQueue rq;
    private ListView lv1;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = new ArrayList<Usuario>();
        rq = Volley.newRequestQueue(this);
        for (int f = 0; f < 5; f++)// Para cargar 10
            cargarPersona();

        lv1=findViewById(R.id.lv1);
        adaptador=new Adaptador(getApplicationContext(), lista);
        lv1.setAdapter(adaptador);

    }

    private void cargarPersona() {
        String url = "https://randomuser.me/api/";
        JsonObjectRequest call = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String valor = response.get("results").toString();
                    JSONArray arreglo = new JSONArray(valor);
                    JSONObject objeto = new JSONObject(arreglo.get(0).toString());
                    String mail = objeto.getString("email");
                    String nombre = objeto.getJSONObject("name").getString("last");
                    String foto = objeto.getJSONObject("picture").getString("large");
                    Usuario usuario = new Usuario(nombre, mail, foto);
                    lista.add(usuario);
                    adaptador.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(call);


    }
}