package com.example.tl01e1_027;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tl01e1_027.Tablas.Personas;
import com.example.tl01e1_027.configuraciones.SQLiteconexion;
import com.example.tl01e1_027.configuraciones.Transacciones;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nombre,telefono,notas,alertaNombre,alertaTelefono,alertaNotas;
    ImageView buttonImagen;
    Button buttonAgregar,buttonDirectorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(EditText) findViewById(R.id.txtNombre);
        telefono=(EditText) findViewById(R.id.txtTelefono);
        notas=(EditText) findViewById(R.id.txtNota);
        buttonAgregar = (Button) findViewById(R.id.btnAgregar);
        buttonDirectorio = (Button) findViewById(R.id.btnDirectorio);
        buttonImagen = (ImageView)findViewById(R.id.btnImage);

        buttonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder nombre = new AlertDialog.Builder(MainActivity.this);
                nombre.setCancelable(true);
                nombre.setTitle("Alerta");
                nombre.setMessage("Debe escribir un Nombre");
                AlertDialog.Builder telefono = new AlertDialog.Builder(MainActivity.this);
                telefono.setCancelable(true);
                telefono.setTitle("Alerta");
                telefono.setMessage("Debe escribir un Telefono");
                AlertDialog.Builder nota = new AlertDialog.Builder(MainActivity.this);
                nota.setCancelable(true);
                nota.setTitle("Alerta");
                nota.setMessage("Debe escribir un Nota");

                nombre.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertaNombre =(EditText)findViewById(R.id.txtNombre);
                alertaTelefono = (EditText)findViewById(R.id.txtTelefono);
                alertaNotas = (EditText)findViewById(R.id.txtNota);
                nombre.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertaNombre.setVisibility(View.VISIBLE);
                    }
                });
                telefono.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                telefono.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertaTelefono.setVisibility(View.VISIBLE);
                    }
                });
                nota.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                nota.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertaNotas.setVisibility(View.VISIBLE);
                    }
                });
                if (TextUtils.isEmpty(alertaNombre.getText().toString())) {
                    nombre.show();
                } else if (alertaTelefono.length() == 0) {
                    telefono.show();
                } else if (TextUtils.isEmpty(alertaNotas.getText().toString())) {
                    nota.show();
                } else
                AgregarPersonas();
            }
        });
        buttonDirectorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,directorio.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            ImageView imageButton = findViewById(R.id.btnImage);
            imageButton.setImageURI(selectedImage);

        }
    }
    private void AgregarPersonas() {
        SQLiteconexion conexion = new SQLiteconexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Transacciones.nombre,nombre.getText().toString());
        values.put(Transacciones.telefono,telefono.getText().toString());
        values.put(Transacciones.notas,notas.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas,Transacciones.id,values);
        Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void limpiarCampos() {
        nombre.setText("");
        telefono.setText("");
        notas.setText("");

    }

}