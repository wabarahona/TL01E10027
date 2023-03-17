package com.example.tl01e1_027.configuraciones;

public class Transacciones
{
    //Nombre de la base de datos
    public static final String NameDatabase = "TL01E1";
     // tablas y objetos
    public static final String tablapersonas = "personas";

    public static String id = "id";
    public static String nombre = "nombre";
    public static String telefono = "telefono";
    public static String notas = "notas";

    public static String CreateTBPersonas = "CREATE TABLE personas(id INTEGER PRIMARY KEY AUTOINCREMENT " +
            "nombre TEXT, telefono INTEGER, notas TEXT  )";

    public static String DropTBPersonas ="DROP TABLE IF EXISTS personas";


}
