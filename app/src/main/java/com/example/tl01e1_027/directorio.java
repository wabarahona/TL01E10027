package com.example.tl01e1_027;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tl01e1_027.Tablas.Personas;
import com.example.tl01e1_027.configuraciones.SQLiteconexion;
import com.example.tl01e1_027.configuraciones.Transacciones;

import java.util.ArrayList;

public class directorio extends AppCompatActivity {
    SQLiteconexion conexion;
    ListView listView;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;
    Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);

        conexion = new SQLiteconexion(this, Transacciones.NameDatabase, null, 1);
        listView = (ListView)findViewById(R.id.listPersonas);
        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersonas);
        listView.setAdapter(adp);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int listItem, long id) {

                new AlertDialog.Builder(directorio.this).setTitle("Quieres remover el contacto  " + ArregloPersonas.get(listItem) + "  de la lista?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ArregloPersonas.remove(listItem);
                                adp.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                return false;
            }
        });

        buttonBack = (Button) findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(view -> onBackPressed());


        }

       private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;
        lista = new ArrayList<Personas>();
        Cursor cursor = db.rawQuery("Select * From " + Transacciones.tablapersonas, null);
        while (cursor.moveToNext()) {
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getInt(2));
            persona.setNotas(cursor.getString(3));

            lista.add(persona);

        }
        cursor.close();
        filllist();

    }
      private void filllist() {

        ArregloPersonas = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            ArregloPersonas.add(lista.get(i).getId() + "  |  " +
                    lista.get(i).getNombre() + "  |  " +
                    lista.get(i).getTelefono() + "  |  " +
                    lista.get(i).getNotas() + "  |  ");
        }


    }
}