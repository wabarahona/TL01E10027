package com.example.tl01e1_027.configuraciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteconexion extends SQLiteOpenHelper {
    public SQLiteconexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Transacciones.CreateTBPersonas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,  int i, int i1) {
        sqLiteDatabase.execSQL(Transacciones.DropTBPersonas);
    }
}
