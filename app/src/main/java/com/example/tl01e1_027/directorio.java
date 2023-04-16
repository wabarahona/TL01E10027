package com.example.tl01e1_027;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tl01e1_027.Tablas.Personas;
import com.example.tl01e1_027.configuraciones.SQLiteconexion;
import com.example.tl01e1_027.configuraciones.Transacciones;

import java.util.ArrayList;

public class directorio extends AppCompatActivity {
    SQLiteconexion conexion;
    SearchView searchbar;
    ListView listView;
    ArrayList<Personas> lista, list;
    ArrayList<String> ArregloPersonas;
    Button buttonBack,buttonCompartir,buttonViewImage,buttonEliminar,buttonActualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);


        conexion = new SQLiteconexion(this, Transacciones.NameDatabase, null, 1);
        listView = (ListView)findViewById(R.id.listPersonas);
        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersonas);
        listView.setAdapter(adp);
        searchbar=(SearchView) findViewById(R.id.search_bar);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adp.getFilter().filter(s);

                return false;
            }
        });


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
        buttonCompartir =(Button)findViewById(R.id.btnCompartir);
        buttonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonViewImage=(Button)findViewById(R.id.btnVerImagen);
        buttonEliminar=(Button)findViewById(R.id.btnEliminar);
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonActualizar=(Button)findViewById(R.id.btnActualizar);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               lista.get(position);
               Integer contactnumber;
               contactnumber = lista.get(position).getTelefono();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ contactnumber));
                startActivity(intent);
                return false;
            }
        });


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
                    lista.get(i).getNombre() );
        }


    }

}