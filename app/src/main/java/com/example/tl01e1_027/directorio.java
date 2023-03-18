package com.example.tl01e1_027;

import static java.lang.Character.toLowerCase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tl01e1_027.Tablas.Personas;
import com.example.tl01e1_027.configuraciones.SQLiteconexion;
import com.example.tl01e1_027.configuraciones.Transacciones;

import java.util.ArrayList;
import java.util.Locale;

public class directorio extends AppCompatActivity {
    SQLiteconexion conexion;
    ListView listView;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);
        searchView=findViewById(R.id.search_bar);
        searchView.clearFocus();
        conexion = new SQLiteconexion(this, Transacciones.NameDatabase, null, 1);
        listView = (ListView) findViewById(R.id.listPersonas);
        ObtenerListaPersonas();

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